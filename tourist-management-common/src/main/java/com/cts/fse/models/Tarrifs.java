package com.cts.fse.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
public class Tarrifs {
    private String tarrifPlace;
    @Min(value = 50000)
    @Max(value = 100000)
    private Long cost;
}
