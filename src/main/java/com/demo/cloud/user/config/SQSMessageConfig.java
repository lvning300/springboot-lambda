package com.demo.cloud.user.config;


import com.demo.cloud.user.component.NotifyExecutor;
import com.demo.cloud.user.constant.QueueConstant;
import com.demo.cloud.user.exchange.ExchangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class SQSMessageConfig {


    @Autowired
    NotifyExecutor notifyExecutor;

    @SqsListener(value = {QueueConstant.QX_AWS_SQS_REQUEST_KEY, QueueConstant.QX_AWS_SQS_RESPONSE_KEY}, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    //@SqsListener(value = {"qx-test-request", "qx-test-response"}, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onReceiverNotifyMessage(ExchangeMessage payload) {
        notifyExecutor.receiveLogic(payload);
    }

}
