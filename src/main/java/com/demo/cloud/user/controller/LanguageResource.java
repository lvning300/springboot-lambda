package com.demo.cloud.user.controller;

import com.demo.cloud.user.model.Language;
import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class LanguageResource {


    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/languages", method = RequestMethod.GET)
    public List<Language> listLambdaLanguages() {
        return Arrays.asList(new Language("node"), new Language("java"), new Language("python"),new Language("go"));
    }

    @RequestMapping(path = "/devops", method = RequestMethod.GET)
    public List<Language> devOps() {
        return Arrays.asList(new Language("JavaDevOps"), new Language("GoDevOps"), new Language("pythonDevOps"));
    }


    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<UserInfo> queryAllUserInfo() {

        return userService.queryAllUserInfo();
    }


}