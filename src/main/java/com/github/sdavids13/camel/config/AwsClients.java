package com.github.sdavids13.camel.config;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsClients {

    @Bean(name = "sqsClient")
    public AmazonSQSClient sqsClient() {
        return new AmazonSQSClient();
    }

    @Bean(name = "snsClient")
    public AmazonSNSClient snsClient() {
        return new AmazonSNSClient();
    }

}
