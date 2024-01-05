package com.llh.traveldog.service.impl;

import java.util.ArrayList;
import java.util.List;

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
    public List<PlaceResponseDto> getPlaceList() {
        List<Place> places = placeDAO.selectPlaceAll();
        List<PlaceResponseDto> placeResponseDtos = new ArrayList<>();

        for (Place place: places) {
            PlaceResponseDto placeResponseDto = new PlaceResponseDto();
            placeResponseDto.setPk(place.getPk());
            placeResponseDto.setName(place.getName());
            placeResponseDto.setDescription(place.getDescription());
            placeResponseDto.setAddress(place.getAddress());
            placeResponseDto.setCoordinate(place.getCoordinate());
            placeResponseDtos.add(placeResponseDto);
        }

        return placeResponseDtos;
    }

    @Override
    public PlaceResponseDto getPlace(Long pk) {
        Place place = placeDAO.selectPlace(pk);

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(place.getPk());
        placeResponseDto.setName(place.getName());
        placeResponseDto.setDescription(place.getDescription());
        placeResponseDto.setAddress(place.getAddress());
        placeResponseDto.setCoordinate(place.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public PlaceResponseDto savePlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setAddress(placeDto.getAddress());
        place.setCoordinate(placeDto.getCoordinate());

        Place savedPlace = placeDAO.insertPlace(place);

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(savedPlace.getPk());
        placeResponseDto.setName(savedPlace.getName());
        placeResponseDto.setDescription(savedPlace.getDescription());
        placeResponseDto.setAddress(savedPlace.getAddress());
        placeResponseDto.setCoordinate(savedPlace.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public PlaceResponseDto updatePlace(UpdatePlaceDto updatePlaceDto) throws Exception{
        Place updatedPlace = placeDAO.updatePlace(
            updatePlaceDto.getPk(),
            updatePlaceDto.getName(),
            updatePlaceDto.getDescription(),
            updatePlaceDto.getAddress(),
            updatePlaceDto.getCoordinate()
        );

        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setPk(updatedPlace.getPk());
        placeResponseDto.setName(updatedPlace.getName());
        placeResponseDto.setDescription(updatedPlace.getDescription());
        placeResponseDto.setAddress(updatedPlace.getAddress());
        placeResponseDto.setCoordinate(updatedPlace.getCoordinate());

        return placeResponseDto;
    }

    @Override
    public void deletePlace(Long pk) throws Exception {
        placeDAO.deletePlace(pk);
    }
}
