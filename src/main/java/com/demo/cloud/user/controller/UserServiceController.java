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

import java.util.*;


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

    @ApiOperation(value = "添加用户信息", response = Integer.class)
    @RequestMapping(path = "/batch/{recorder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer addUserInfoBatch(@PathVariable(value = "recorder",name ="recorder")
                                    Integer recorder) {



        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < recorder; i++) {
            userInfos.add(UserInfo.builder()
                    .nickName(randomChar()+i)
                    .orgName(randomChar()+i)
                    .userName(randomChar()+i)
                    .password(randomChar()+i)
                    .b1(randomChar())
                    .b2(randomChar())
                    .b3(randomChar())
                    .b4(randomChar())
                    .b5(randomChar())
                    .b6(randomChar())
                    .b7(randomChar())
                    .b8(randomChar())
                    .b9(randomChar())
                    .b10(randomChar())
                    .b11(randomChar())
                    .b12(randomChar())
                    .b13(randomChar())
                    .b14(randomChar())
                    .b15(randomChar())
                    .b16(randomChar())
                    .b17(randomChar())
                    .b18(randomChar())
                    .b19(randomChar())
                    .b20(randomChar())
                    .b21(randomChar())
                    .b22(randomChar())
                    .b23(randomChar())
                    .b24(randomChar())
                    .b25(randomChar())
                    .b26(randomChar())
                    .b27(randomChar())
                    .b28(randomChar())
                    .b29(randomChar())
                    .b30(randomChar())
                    .build());
        }
        return userService.insertBatchUserInfo(userInfos);

    }

    public static String randomChar() {



        char[] aZ = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(aZ[random.nextInt(32)]);
        }
        return builder.toString();

    }

}
