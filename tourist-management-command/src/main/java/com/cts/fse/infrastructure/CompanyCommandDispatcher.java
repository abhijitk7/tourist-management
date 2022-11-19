package com.cts.fse.infrastructure;

import com.cts.fse.command.BaseCommand;
import com.cts.fse.command.CommandHandlerMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
            log.error("No command handler was registered!");
            throw new RuntimeException("No command handler was registered!");
        }
        if (handlers.size() > 1) {
            log.error("Cannot send command to more than one handler!");
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(baseCommand);

    }
}
