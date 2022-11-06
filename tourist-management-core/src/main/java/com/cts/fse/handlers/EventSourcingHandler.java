package com.cts.fse.handlers;

import com.cts.fse.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);

    T getById(String id);
}
