package com.demo.cloud.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.cloud.user.config.QueueNames;
import com.demo.cloud.user.dto.MessageDTO;
import com.demo.cloud.user.exchange.ExchangeAction;
import com.demo.cloud.user.exchange.ExchangeMessage;
import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class UserServiceController {

    private final IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @Autowired
    private QueueNames queueNames;

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
    @RequestMapping(path = "/user-page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserInfo> queryUserInfoByPage(@RequestBody UserInfo userInfo) {
        log.info("### query param:{}", JSONObject.toJSON(userInfo));
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

    @RequestMapping(path = "/send/sqs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sendSQS(@RequestBody MessageDTO messageDTO) {
        log.info("### param messageDTO:{}", JSONObject.toJSON(messageDTO));
        messagingTemplate.convertAndSend(queueNames.getRequestQueue(),
                ExchangeMessage.builder()
                        .action(ExchangeAction.ADD_ORDER_ACTION)
                        .data(messageDTO)
                        .build());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }


    @RequestMapping(path = "/test/send/sqs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sendSQS() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        messagingTemplate.convertAndSend(queueNames.getRequestQueue(),
                ExchangeMessage.builder()
                        .action(ExchangeAction.ADD_ORDER_ACTION)
                        .data(resultMap)
                        .build());

        return resultMap;
    }

    @ApiOperation(value = "添加用户信息", response = UserInfo.class)
    @RequestMapping(path = "/user-info", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer addUserInfo(@RequestBody UserInfo userInfo) {
        log.info("### add user param:{}", JSONObject.toJSON(userInfo));
        return userService.insertUserInfo(userInfo);

    }

}
