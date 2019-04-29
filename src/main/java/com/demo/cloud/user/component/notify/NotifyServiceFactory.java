package com.demo.cloud.user.component.notify;

import com.demo.cloud.user.component.notify.impl.CancelOrderServiceImpl;
import com.demo.cloud.user.component.notify.impl.NotificationImpl;
import com.demo.cloud.user.config.QueueNames;
import com.demo.cloud.user.exchange.ExchangeAction;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

import java.util.Objects;


public class NotifyServiceFactory {


    public static NotifyService getService(String type, String content,
                                           QueueMessagingTemplate template,
                                           QueueNames queueNames) {

        if (Objects.equals(type, ExchangeAction.ADD_ORDER_ACTION)) {
            return new NotificationImpl(content, template, queueNames);
        } if (Objects.equals(type, ExchangeAction.CANCEL_ORDER_ACTION)) {
            return new CancelOrderServiceImpl(content, template, queueNames);
        } else {
            throw new RuntimeException("没有匹配的可处理的消息类型处理服务.");
        }

    }
}
