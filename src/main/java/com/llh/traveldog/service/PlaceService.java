package com.llh.traveldog.service;

import com.llh.traveldog.data.dto.PlaceDto;
import com.llh.traveldog.data.dto.PlaceResponseDto;
import com.llh.traveldog.data.dto.UpdatePlaceDto;

public interface PlaceService {
    PlaceResponseDto getPlace(Long pk);

    PlaceResponseDto savePlace(PlaceDto placeDto);

    PlaceResponseDto updatePlace(UpdatePlaceDto updatePlaceDto) throws Exception;

    void deletePlace(Long pk) throws Exception;
}
