package com.cts.fse.producers;

import com.cts.fse.events.BaseEvent;

public interface EventProducer {
    void produce(String topicName, BaseEvent baseEvent);
}
