package com.github.sdavids13.camel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "aws.bridge")
public class MessagingBridge {

    private Map<String, String> jmsToSns = new HashMap<>();
    private Map<String, String> sqsToJms = new HashMap<>();
    private Map<String, String> jmsToSqs = new HashMap<>();

    public Map<String, String> getJmsToSns() {
        return jmsToSns;
    }

    public void setJmsToSns(Map<String, String> jmsToSns) {
        this.jmsToSns = jmsToSns;
    }

    public Map<String, String> getSqsToJms() {
        return sqsToJms;
    }

    public void setSqsToJms(Map<String, String> sqsToJms) {
        this.sqsToJms = sqsToJms;
    }

    public Map<String, String> getJmsToSqs() {
        return jmsToSqs;
    }

    public void setJmsToSqs(Map<String, String> jmsToSqs) {
        this.jmsToSqs = jmsToSqs;
    }
}
