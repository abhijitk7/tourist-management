package com.cts.fse.infrastructure;

import com.cts.fse.domain.AggregateRoot;
import com.cts.fse.domain.CompanyAggregate;
import com.cts.fse.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class CompanyEventSourcingHandler implements EventSourcingHandler<CompanyAggregate> {

    @Autowired
    EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvent(aggregateRoot.getId(), aggregateRoot.getUncommitedChanges(), aggregateRoot.getVersion(), CompanyAggregate.class.getTypeName());
        aggregateRoot.markChangesAsCommitted();
    }

    @Override
    public CompanyAggregate getById(String id) {
        var aggregate = new CompanyAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(event -> event.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
