package com.cts.fse.api.dto;

import com.cts.fse.dto.BaseResponse;
import com.cts.fse.models.TouristPlaces;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlacesLookUpResponse extends BaseResponse {
    private List<TouristPlaces> touristPlaces;

    public PlacesLookUpResponse(String message) {
        super(message);
    }
}
