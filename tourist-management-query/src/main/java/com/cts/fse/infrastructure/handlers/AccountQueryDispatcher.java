package com.cts.fse.infrastructure.handlers;

import com.cts.fse.domain.BaseEntity;
import com.cts.fse.infrastructure.QueryDispatcher;
import com.cts.fse.queries.BaseQuery;
import com.cts.fse.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handlerMethod) {
        var handler = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handler.add(handlerMethod);
    }

    @Override
    public <T extends BaseEntity> List<T> send(BaseQuery query) {
        var handlers = this.routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0) {
            throw new RuntimeException("No query handler was registered");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Can't send query to one or more handlers");
        }
        return handlers.get(0).handle(query);
    }
}
