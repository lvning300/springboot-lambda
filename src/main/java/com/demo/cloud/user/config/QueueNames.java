package com.demo.cloud.user.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.demo.cloud.user.constant.QueueConstant.*;

/**
 * 队列名称自动创建
 */
@Component
@Data
@ToString
@NoArgsConstructor
@ConditionalOnClass({QueueMessagingTemplate.class})
@SuppressWarnings("ALL")
@Configuration
public class QueueNames {


    @Value(QX_AWS_SQS_REQUEST_KEY)
    String requestQueue;

    @Value(QX_AWS_SQS_RESPONSE_KEY)
    String responseQueue;

    @Value(QX_AWS_SQS_RESPONSE_TEST_KEY)
    String requestQueueTest;

}
