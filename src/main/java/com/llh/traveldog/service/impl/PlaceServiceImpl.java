package com.llh.traveldog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llh.traveldog.data.dao.PlaceDAO;
import com.llh.traveldog.data.dto.PlaceDto;
import com.llh.traveldog.data.dto.PlaceResponseDto;
import com.llh.traveldog.data.dto.UpdatePlaceDto;
import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDAO placeDAO;

    @Autowired
    public PlaceServiceImpl(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    @Override
    public PlaceResponseDto getPlace(Long pk) {
        Place place = placeDAO.selectPlace(pk);

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(place.getPk());
        placeResponseDto.setName(place.getName());
        placeResponseDto.setCoordinate(place.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public PlaceResponseDto savePlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setName(placeDto.getName());
        place.setCoordinate(placeDto.getCoordinate());

        Place savedPlace = placeDAO.insertPlace(place);

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(savedPlace.getPk());
        placeResponseDto.setName(savedPlace.getName());
        placeResponseDto.setCoordinate(savedPlace.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public PlaceResponseDto updatePlace(UpdatePlaceDto updatePlaceDto) throws Exception{
        Place updatedPlace = placeDAO.updatePlace(
            updatePlaceDto.getPk(),
            updatePlaceDto.getName(),
            updatePlaceDto.getCoordinate()
        );

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(updatedPlace.getPk());
        placeResponseDto.setName(updatedPlace.getName());
        placeResponseDto.setCoordinate(updatedPlace.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public void deletePlace(Long pk) throws Exception {
        placeDAO.deletePlace(pk);
    }
}
