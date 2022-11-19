package com.cts.fse.infrastructure;

import com.cts.fse.domain.CompanyAggregate;
import com.cts.fse.domain.EventStoreRepository;
import com.cts.fse.events.BaseEvent;
import com.cts.fse.events.EventModel;
import com.cts.fse.exceptions.AggregateNotFoundException;
import com.cts.fse.exceptions.ConurrencyException;
import com.cts.fse.producers.EventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyEventStore implements EventStore {

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion, String aggregateType) {
        var eventStream = this.eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            log.error("Error occurred while saving event data because of concurrent update or insert");
            throw new ConurrencyException();
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(CompanyAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var savedEvent = this.eventStoreRepository.save(eventModel);
            if (!savedEvent.getId().isEmpty()) {
                this.eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = this.eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            log.error("Incorrect company id provided");
            throw new AggregateNotFoundException("Incorrect company id provided");
        }
        return eventStream.stream().map(data -> data.getEventData()).collect(Collectors.toList());
    }
}
