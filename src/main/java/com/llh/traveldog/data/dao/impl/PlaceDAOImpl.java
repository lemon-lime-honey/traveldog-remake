package com.llh.traveldog.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.locationtech.jts.geom.Coordinate;

import com.llh.traveldog.data.dao.PlaceDAO;
import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.data.repository.PlaceRepository;

@Component
public class PlaceDAOImpl implements PlaceDAO {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceDAOImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place insertPlace(Place place) {
        if (place.getCoordinate() == null) {
            place.setCoordinate(new Coordinate(37.5759, 126.9768));
        }
        Place savedPlace = placeRepository.save(place);

        return savedPlace;
    }

    @Override
    public List<Place> selectPlaceAll() {
        List<Place> allPlaces = placeRepository.findAll();

        return allPlaces;
    }

    @Override
    public Place selectPlace(Long pk) {
        Place selectedPlace = placeRepository.getReferenceById(pk);

        return selectedPlace;
    }

    @Override
    public Place updatePlace(Long pk, String name, Coordinate coordinate) throws Exception {
        if (name == null && coordinate == null) {
            throw new Exception();
        }

        Optional<Place> selectedPlace = placeRepository.findById(pk);

        Place updatedPlace;

        if (selectedPlace.isPresent()) {
            Place place = selectedPlace.get();

            place.setName(name);

            if (coordinate != null) {
                place.setCoordinate(coordinate);
            }

            updatedPlace = placeRepository.save(place);
        } else {
            throw new Exception();
        }

        return updatedPlace;
    }

    @Override
    public void deletePlace(Long pk) throws Exception {
        Optional<Place> selectedPlace = placeRepository.findById(pk);

        if (selectedPlace.isPresent()) {
            Place place = selectedPlace.get();
            placeRepository.delete(place);
        } else {
            throw new Exception();
        }
    }
}
