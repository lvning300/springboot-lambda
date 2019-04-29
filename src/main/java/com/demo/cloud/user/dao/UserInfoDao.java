package com.demo.cloud.user.dao;

import com.demo.cloud.user.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

import java.util.List;

public interface UserInfoDao extends SqlServerMapper<UserInfo>, BaseSelectMapper<UserInfo>, ExampleMapper<UserInfo>,
        RowBoundsMapper<UserInfo>,
        Marker, BaseUpdateMapper<UserInfo>,
        BaseDeleteMapper<UserInfo>, MySqlMapper<UserInfo> {

    List<UserInfo> selectByPageNumSize(@Param("userInfo") UserInfo userInfo,
                                       @Param("pageNum") int pageNum,
                                       @Param("pageSize") int pageSize);

    List<UserInfo> selectAllUser(@Param("userInfo") UserInfo userInfo);



}
