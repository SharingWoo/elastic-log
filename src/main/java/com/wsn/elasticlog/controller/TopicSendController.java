package com.wsn.elasticlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sharing
 * @version 1.0.0
 * @create 2019-01-03 下午5:20
 **/
@RestController
@RequestMapping("/topic")
public class TopicSendController {

    @Value("${kafka.log.topicName}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TopicSendController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping("/")
    public void sendMessage() {
        String messageContext = "hello";
        kafkaTemplate.send(topicName, messageContext);
    }
}
