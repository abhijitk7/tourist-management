package com.cts.fse.api.dto;

import com.cts.fse.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailsResponse extends BaseResponse {
    private String id;

    public CompanyDetailsResponse(String message, String id) {
        super(message);
        this.id = id;
    }

}
