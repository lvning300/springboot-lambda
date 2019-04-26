package com.demo.cloud.user.config;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
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


@Slf4j
@Configuration
@EnableConfigurationProperties(AWSProperties.class)
public class AwsConfig {


    private final AWSProperties properties;

    public AwsConfig(AWSProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {

        AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();

        log.info("### bxc Credentials Type : {} ", provider.getCredentials().getClass().getName());

        return provider;
    }

    @Bean
    public AmazonSQSAsync amazonSQS(AWSCredentialsProvider provider) {

        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setMaxErrorRetry(3);

        AmazonSQSAsync sqsAsync = AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(provider)
                .withRegion(properties.getRegion().trim())
                .withClientConfiguration(configuration)
                .build();

        sqsAsync.createQueue("test_qx_queue_request");
        sqsAsync.createQueue("test_qx_queue_response");

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
        QueueMessageHandlerFactory queueMsgHandlerFactory = new QueueMessageHandlerFactory();
        queueMsgHandlerFactory.setAmazonSqs(amazonSQSAsync);

        QueueMessageHandler queueMessageHandler = queueMsgHandlerFactory.createQueueMessageHandler();

        return queueMessageHandler;
    }

}
