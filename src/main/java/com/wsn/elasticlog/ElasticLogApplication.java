package com.wsn.elasticlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author sharing
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ElasticLogApplication implements ApplicationRunner {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ElasticLogApplication(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send("log1", msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticLogApplication.class, args);
    }

    @KafkaListener(topics = "log1", groupId = "test-consumer-group")
    public void listen(String message) {
        System.out.println("Received message in group - group - id:" + message);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessage("Hi Welcome to Spring For Apache Kafka");
    }
}

