package com.demo.cloud.user.config;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.demo.cloud.user.properties.AWSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;

import java.util.Collections;


@Slf4j
@Configuration
@EnableConfigurationProperties(AWSProperties.class)
public class AwsConfig {


    private final AWSProperties properties;

    public AwsConfig(AWSProperties properties) {
        this.properties = properties;
    }


    @Bean
    public AWSCredentials awsCredentials() {

        //AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();
        AWSCredentials awsCredentials = new BasicAWSCredentials(properties.getAwsAccessKeyId(), properties.getAwsSecretKey());
        log.info("### bxc Credentials Type : {} ", awsCredentials.getClass().getName());

        return awsCredentials;
    }

    @Bean
    public AmazonSQSAsync amazonSQS(AWSCredentials awsCredentials, QueueNames queueNames) {

        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setMaxErrorRetry(3);

        AmazonSQSAsync sqsAsync = AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(properties.getRegion().trim())
                .withClientConfiguration(configuration)
                .build();

        sqsAsync.createQueue(queueNames.getRequestQueue());
        sqsAsync.createQueue(queueNames.getResponseQueue());
        sqsAsync.createQueue(queueNames.getRequestQueueTest());
        return sqsAsync;
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {

        QueueMessagingTemplate template = new QueueMessagingTemplate(amazonSQSAsync);

        return template;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainer msgListenerContainer = simpleMessageListenerContainerFactory(amazonSQSAsync).createSimpleMessageListenerContainer();
        msgListenerContainer.setMessageHandler(queueMessageHandler(amazonSQSAsync));

        return msgListenerContainer;
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory msgListenerContainerFactory = new SimpleMessageListenerContainerFactory();
        msgListenerContainerFactory.setAmazonSqs(amazonSQSAsync);
        msgListenerContainerFactory.setMaxNumberOfMessages(10);
        msgListenerContainerFactory.setWaitTimeOut(10);

        return msgListenerContainerFactory;
    }

    @Bean
    public QueueMessageHandler queueMessageHandler(AmazonSQSAsync amazonSQSAsync) {
        QueueMessageHandlerFactory queueMsgHandlerFactory = queueMessageHandlerFactory();
        queueMsgHandlerFactory.setAmazonSqs(amazonSQSAsync);

        QueueMessageHandler queueMessageHandler = queueMsgHandlerFactory.createQueueMessageHandler();

        return queueMessageHandler;
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

        //set strict content type match to false
        messageConverter.setStrictContentTypeMatch(false);
        factory.setArgumentResolvers(Collections.singletonList(new PayloadArgumentResolver(messageConverter)));
        return factory;
    }

}
