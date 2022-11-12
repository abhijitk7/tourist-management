package com.cts.fse.api.dto;

import com.cts.fse.dto.BaseResponse;
import com.cts.fse.models.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyLookUpResponse extends BaseResponse {
    private List<Company> companies;

    public CompanyLookUpResponse(String message) {
        super(message);
    }
}
