package com.cts.fse.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class CompanyTariffs {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String tariffPlace;

    @Min(value = 50000)
    @Max(value = 200000)
    private Long cost;

    @Column(name = "company_id", insertable = false, updatable = false)
    private String companyId;

}
