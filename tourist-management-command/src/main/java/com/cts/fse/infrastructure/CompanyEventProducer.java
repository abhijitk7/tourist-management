package com.cts.fse.infrastructure;

import com.cts.fse.events.BaseEvent;
import com.cts.fse.producers.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;

public class CompanyEventProducer implements EventProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topicName, BaseEvent event) {
        this.kafkaTemplate.send(topicName, event);
    }
}
