package com.cts.fse.infrastructure;

import com.cts.fse.domain.BaseEntity;
import com.cts.fse.queries.BaseQuery;
import com.cts.fse.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handlerMethod);

    <T extends BaseEntity> List<T> send(BaseQuery query);
}
