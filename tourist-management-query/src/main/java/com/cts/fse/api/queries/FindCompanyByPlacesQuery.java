package com.cts.fse.api.queries;

import com.cts.fse.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCompanyByPlacesQuery extends BaseQuery {
    private String touristPlace;
}
