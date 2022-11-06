package com.cts.fse.domain;

import com.cts.fse.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AggregateRoot {
    private final List<BaseEvent> changes = new ArrayList<>();
    @Getter
    protected String id;
    @Getter
    @Setter
    private int version = -1;

    public List<BaseEvent> getUncommitedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.warn(MessageFormat.format(
                    "The apply method was not found in the aggregate for {0}",
                    event.getClass().getName()));
        } catch (Exception e) {
            log.error("Error applying event to aggregate", e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        this.applyChange(event, true);
    }


    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }
}
