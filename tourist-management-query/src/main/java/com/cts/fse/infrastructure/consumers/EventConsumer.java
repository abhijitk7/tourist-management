package com.cts.fse.infrastructure.consumers;

import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload CompanyAddedEvent event, Acknowledgment ack);

    void consume(@Payload CompanyUpdatedEvent event, Acknowledgment ack);
}
