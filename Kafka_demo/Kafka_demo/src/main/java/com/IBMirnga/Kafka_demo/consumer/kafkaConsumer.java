package com.IBMirnga.Kafka_demo.consumer;

import com.IBMirnga.Kafka_demo.payload.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class kafkaConsumer {

    @KafkaListener(topics = "ibrahim", groupId = "myGroup")
    public void consumerMsg(String msg) {
        log.info(String.format("Consuming the message from ibrahim Topic:: %s", msg));
    }

    @KafkaListener(topics = "ibrahim", groupId = "myGroup")
    public void consumerJsonMsg(Student student) {
        log.info(String.format("Consuming the message from ibrahim Topic:: %s", student.toString()));
    }
}
