package com.cts.fse.infrastructure;

import com.cts.fse.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion, String aggregateType);

    List<BaseEvent> getEvents(String aggregateId);
}
