package com.IBMirnga.Kafka_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class kafkaTopicConfig {

    @Bean
    public NewTopic myTopic() {
        return TopicBuilder
                .name("ibrahim")
                .build();
    }
}
