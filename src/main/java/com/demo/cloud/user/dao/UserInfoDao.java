package com.demo.cloud.user.dao;

import com.demo.cloud.user.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserInfoDao extends Mapper<UserInfo>, MySqlMapper<UserInfo> {

    List<UserInfo> selectByPageNumSize(@Param("userInfo") UserInfo userInfo,
                                       @Param("pageNum") int pageNum,
                                       @Param("pageSize") int pageSize);


}
