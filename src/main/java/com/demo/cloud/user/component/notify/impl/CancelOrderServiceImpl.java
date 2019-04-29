package com.demo.cloud.user.component.notify.impl;

import com.demo.cloud.user.component.notify.NotifyService;
import com.demo.cloud.user.config.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;


@Slf4j
public class CancelOrderServiceImpl implements NotifyService {

    private String content;
    private QueueMessagingTemplate template;
    private QueueNames queueNames;


    public CancelOrderServiceImpl(String content,
                                  QueueMessagingTemplate template,
                                  QueueNames queueNames) {
        this.content = content;
        this.template = template;
        this.queueNames = queueNames;
    }

    @Override
    public void exec() {
        log.info("### CANCEL Order ServiceImpl,content:{}", content);
    }
}
