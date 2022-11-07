package com.cts.fse.infrastructure.consumers;

import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import com.cts.fse.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "CompanyAddedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CompanyAddedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "CompanyUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CompanyUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
