package com.github.sdavids13.camel;

import com.github.sdavids13.camel.config.MessagingBridge;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MessagingBridge.class)
public class SpringBootAwsCamelApplication extends FatJarRouter {

    @Autowired
    MessagingBridge bridgeConfig;

    @Override
    public void configure() throws Exception {
        bridgeConfig.getJmsToSns().forEach((jmsChannel, snsChannel) -> {
            from("jms:" + jmsChannel).to("aws-sns:" + snsChannel + "?amazonSNSClient=#snsClient");
        });

        bridgeConfig.getSqsToJms().forEach((sqsChannel, jmsChannel) -> {
            from("aws-sqs:" + sqsChannel + "?amazonSQSClient=#sqsClient&maxMessagesPerPoll=10&concurrentConsumers=1&defaultVisibilityTimeout=15").to("jms:" + jmsChannel);
        });

        bridgeConfig.getJmsToSqs().forEach((jmsChannel, sqsChannel) -> {
            from("jms:" + jmsChannel).removeHeaders("*", "JMSMessageID", "breadcrumbId").to("aws-sqs:" + sqsChannel + "?amazonSQSClient=#sqsClient");
        });
    }

}
