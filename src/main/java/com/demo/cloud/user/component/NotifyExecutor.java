package com.demo.cloud.user.component;


import com.alibaba.fastjson.JSONObject;
import com.demo.cloud.user.component.notify.NotifyServiceFactory;
import com.demo.cloud.user.config.QueueNames;
import com.demo.cloud.user.exchange.ExchangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;


@Component
@Slf4j
public class NotifyExecutor {

    @Autowired
    QueueMessagingTemplate messagingTemplate;

    @Autowired
    QueueNames queueNames;

    public void receiveLogic(ExchangeMessage payload) {
        String action = payload.getAction();
        log.info("###### Message Action:{}", action);
        String content = JSONObject.toJSONString(payload.getData());
        log.info("receiver Message:{}",content);
        NotifyServiceFactory
                .getService(action, content, messagingTemplate, queueNames)
                .exec();
    }

}
