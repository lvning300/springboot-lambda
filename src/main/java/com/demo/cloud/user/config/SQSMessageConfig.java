package com.demo.cloud.user.config;


import com.demo.cloud.user.component.NotifyExecutor;
import com.demo.cloud.user.constant.QueueConstant;
import com.demo.cloud.user.exchange.ExchangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.demo.cloud.user.constant.QueueConstant.QX_AWS_SQS_RESPONSE_KEY;


@Slf4j
@Configuration
@Component
public class SQSMessageConfig {


    @Autowired
    NotifyExecutor notifyExecutor;


    @SqsListener(value = {QueueConstant.QX_AWS_SQS_RESPONSE_TEST_KEY, QX_AWS_SQS_RESPONSE_KEY}, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    //@SqsListener(value = {"qx-test-request", "qx-test-response"}, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void onReceiverNotifyMessage(ExchangeMessage payload) {
        notifyExecutor.receiveLogic(payload);
    }
}
