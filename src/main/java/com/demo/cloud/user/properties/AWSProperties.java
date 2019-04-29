package com.demo.cloud.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties("aws")
public class AWSProperties {

    /**
     * awsAccessKeyId
     */
    String awsAccessKeyId;
    /**
     * awsSecretKey
     */
    String awsSecretKey;

    /**
     * AWS服务区域
     */
    String region;

    /**
     * S3存储名称
     */
    String s3bucketName;



}
