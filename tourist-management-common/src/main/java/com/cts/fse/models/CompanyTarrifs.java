package com.cts.fse.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class CompanyTarrifs {

    @Id
    private Long id;
    private String tarrifPlace;
    @Min(value = 50000)
    @Max(value = 100000)
    private Long cost;
}
