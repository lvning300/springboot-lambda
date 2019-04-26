package com.demo.cloud.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties("bsc.aws")
public class AWSProperties {

    /**
     * AWSf服务区域
     */
    String region;

    /**
     * S3存储名称
     */
    String s3bucketName;



}
