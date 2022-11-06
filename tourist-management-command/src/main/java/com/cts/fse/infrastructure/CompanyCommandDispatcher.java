package com.cts.fse.infrastructure;

import com.cts.fse.command.BaseCommand;
import com.cts.fse.command.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CompanyCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes =
            new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand baseCommand) {
        var handlers = routes.get(baseCommand.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(baseCommand);

    }
}
