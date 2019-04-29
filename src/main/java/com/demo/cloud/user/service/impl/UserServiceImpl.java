package com.demo.cloud.user.service.impl;

import com.demo.cloud.user.dao.UserInfoDao;
import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
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
    public Integer insertUserInfo(UserInfo userInfo) {
        return userInfoDao.insert(userInfo);
    }
}
