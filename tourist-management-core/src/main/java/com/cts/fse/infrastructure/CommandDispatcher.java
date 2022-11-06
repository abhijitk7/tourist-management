package com.cts.fse.infrastructure;

import com.cts.fse.command.BaseCommand;
import com.cts.fse.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand baseCommand);

}
