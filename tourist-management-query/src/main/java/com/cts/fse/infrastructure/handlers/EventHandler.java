package com.cts.fse.infrastructure.handlers;

import com.cts.fse.events.CompanyAddedEvent;
import com.cts.fse.events.CompanyUpdatedEvent;

public interface EventHandler {
    void on(CompanyAddedEvent companyAddedEvent);

    void on(CompanyUpdatedEvent companyUpdatedEvent);
}
