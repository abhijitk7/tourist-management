package com.cts.fse.api.queries;

import com.cts.fse.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllCompaniesQuery query);

    List<BaseEntity> handle(FindCompanyByIdQuery query);

    List<BaseEntity> handle(FindCompanyByNameQuery query);

    List<BaseEntity> handle(FindCompanyByPlacesQuery query);
}
