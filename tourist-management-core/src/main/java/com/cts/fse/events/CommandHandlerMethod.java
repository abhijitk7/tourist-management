package com.cts.fse.events;

import com.cts.fse.command.BaseCommand;


@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
