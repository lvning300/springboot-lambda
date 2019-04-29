package com.demo.cloud.user.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 队列名称自动创建
 */
@Component
@Data
@ToString
@NoArgsConstructor
@ConditionalOnClass({QueueMessagingTemplate.class})
@SuppressWarnings("ALL")
@ConfigurationProperties("aws.sqs.queues")
public class QueueNames {

    String requestQueue;

    String responseQueue;

}
