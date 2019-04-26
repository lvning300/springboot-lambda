package com.demo.cloud.user.controller;

import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class UserServiceController {

    private final IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceController(IUserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "查询所有用户信息", response = UserInfo.class)
    @RequestMapping(path = "/user-list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> queryAllUserInfo() {

        return userService.queryAllUserInfo();
    }

    @ApiOperation(value = "根据用户名查询用户信息", response = UserInfo.class)
    @RequestMapping(path = "/users/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo queryAllUserInfo(@PathVariable(name = "userName") String userName) {

        return userService.queryUserInfoByUserName(userName);
    }


    @ApiOperation(value = "根据UserInfo查询用户信息列表", response = UserInfo.class)
    @RequestMapping(path = "/user-page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> queryUserInfoByPage(@RequestBody UserInfo userInfo) {

        List<UserInfo> userInfos = userService.queryUserInfoByPage(userInfo);
        return userInfos;
    }


    @ApiOperation(value = "使用Mapper框架自动分页根据UserInfo分页查询用户信息", response = UserInfo.class)
    @RequestMapping(path = "/page-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserInfo> queryUserInfoByPageInfo(UserInfo userInfo) {
        List<UserInfo> userInfos = userService.queryUserInfoByPage(userInfo);
        return new PageInfo<>(userInfos);
    }

    @ApiOperation(value = "自定义SQL查询语句根据UserInfo分页查询用户信息", response = UserInfo.class)
    @RequestMapping(path = "/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserInfo> queryUserInfoByPageInfo(UserInfo userInfo,
                                                      @PathVariable(value = "pageNum", name = "pageNum") Integer pageNum,
                                                      @PathVariable(value = "pageSize", name = "pageSize") Integer pageSize) {
        List<UserInfo> userInfos = userService.queryUserInfoByPageSize(userInfo, pageNum, pageSize);
        return new PageInfo<>(userInfos);
    }


    @GetMapping(value = "/redis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getRedisValue() {

        redisTemplate.opsForValue().set("name", "tom");
        redisTemplate.opsForValue().set("age", "10");
        redisTemplate.opsForValue().set("address", "SZ");
        return redisTemplate.opsForValue().get("name");
    }

}
