package com.demo.cloud.user.service;

import com.demo.cloud.user.model.UserInfo;

import java.util.List;

public interface IUserService {

    List<UserInfo> queryAllUserInfo();

    List<UserInfo> queryUserInfoByPage(UserInfo userInfo);

    List<UserInfo> queryUserInfoByPageSize(UserInfo userInfo, Integer pageNum, Integer pageSize);

    UserInfo queryUserInfoByUserName(String userName);
}
