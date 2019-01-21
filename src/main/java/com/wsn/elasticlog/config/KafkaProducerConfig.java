package com.wsn.elasticlog.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka消息生产者
 *
 * @author sharing
 * @version 1.0.0
 * @create 2019-01-03 下午4:36
 **/
@Configuration
public class KafkaProducerConfig {
    @Value("${kafka.address:localhost:9092}")
    private String kafkaAddress;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configMap = new HashMap<>(16);
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configMap);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
