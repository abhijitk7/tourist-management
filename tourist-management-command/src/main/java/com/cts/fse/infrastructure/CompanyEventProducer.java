package com.cts.fse.infrastructure;

import com.cts.fse.events.BaseEvent;
import com.cts.fse.producers.EventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topicName, BaseEvent event) {
        log.info("Message successfully sent to Kafka queue");
        this.kafkaTemplate.send(topicName, event);
    }
}
