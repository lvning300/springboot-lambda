package com.demo.cloud.user.component.notify.impl;

import com.demo.cloud.user.component.notify.NotifyService;
import com.demo.cloud.user.config.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;


@Slf4j
public class NotificationImpl implements NotifyService {

    private String content;
    private QueueMessagingTemplate template;
    private QueueNames queueNames;


    public NotificationImpl(String content,
                            QueueMessagingTemplate template,
                            QueueNames queueNames) {
        this.content = content;
        this.template = template;
        this.queueNames = queueNames;
    }

    @Override
    public void exec() {

//        template.convertAndSend(queueNames.getResponseQueue(),
//                ExchangeMessage.builder()
//                        .action(ExchangeAction.CANCEL_ORDER_ACTION)
//                        .data(content)
//                        .build());
    }
}
