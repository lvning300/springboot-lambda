package com.demo.cloud.user.service.impl;

import com.demo.cloud.user.dao.UserInfoDao;
import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<UserInfo> queryAllUserInfo() {

        return userInfoDao.selectAll();
    }

    @Override
    public List<UserInfo> queryUserInfoByPage(UserInfo userInfo) {

        if (userInfo.getPage() != null && userInfo.getRows() != null) {
            PageHelper.startPage(userInfo.getPage(), userInfo.getRows());
        }

        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        WeekendCriteria<UserInfo, Object> criteria = weekend.weekendCriteria();
        if (!StringUtils.isEmpty(userInfo.getOrgName())) {
            criteria.andLike(UserInfo::getOrgName, "%" + userInfo.getOrgName() + "%");
        }
        if (!StringUtils.isEmpty(userInfo.getNickName())) {
            criteria.andLike(UserInfo::getNickName, "%" + userInfo.getNickName() + "%");
        }

        return userInfoDao.selectByExample(weekend);
    }

    @Override
    public List<UserInfo> queryUserInfoByPageSize(UserInfo userInfo, Integer pageNum, Integer pageSize) {

        return userInfoDao.selectByPageNumSize(userInfo, pageNum, pageSize);
    }

    @Override
    public  UserInfo  queryUserInfoByUserName(String userName) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user_name", userName);
        return userInfoDao.selectOneByExample(example);
    }

    @Override
    public Integer insertBatchUserInfo(List<UserInfo> userInfos) {

        int result = 1;
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        //sqlSessionFactory.getConfiguration().setCacheEnabled(false);
        SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        UserInfoDao mapper = batchSqlSession.getMapper(UserInfoDao.class);

        int batchCount = 1000;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        long start =System.currentTimeMillis();
        for (int index = 0; index < userInfos.size(); ) {
            if (batchLastIndex >= userInfos.size()) {
                batchLastIndex = userInfos.size();
                result = result * mapper.batchInsert(userInfos.subList(index, batchLastIndex));
                batchSqlSession.commit();
                //清理缓存，防止溢出
                batchSqlSession.clearCache();
                //log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
                break;// 数据插入完毕，退出循环
            } else {
                result = result * mapper.batchInsert( userInfos.subList(index, batchLastIndex));
                batchSqlSession.commit();
                //清理缓存，防止溢出
                batchSqlSession.clearCache();
                //log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + (batchCount - 1);
            }
        }
        batchSqlSession.commit();
        batchSqlSession.close();
        long end =System.currentTimeMillis();
        log.info("### Batch耗时:"+(end-start));


        long noStart =System.currentTimeMillis();
        Integer integer = userInfoDao.batchInsertFor(userInfos);
        long noEnd =System.currentTimeMillis();
        log.info("### 非Batch耗时:"+(noEnd-noStart));
        return result;

    }

}
