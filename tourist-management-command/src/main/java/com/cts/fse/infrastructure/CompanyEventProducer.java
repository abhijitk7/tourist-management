package com.cts.fse.infrastructure;

import com.cts.fse.events.BaseEvent;
import com.cts.fse.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topicName, BaseEvent event) {
        this.kafkaTemplate.send(topicName, event);
    }
}
