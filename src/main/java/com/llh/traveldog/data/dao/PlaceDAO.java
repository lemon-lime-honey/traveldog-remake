package com.llh.traveldog.data.dao;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import com.llh.traveldog.data.entity.Place;

public interface PlaceDAO {
    Place insertPlace(Place place);

    List<Place> selectPlaceAll();

    Place selectPlace(Long pk);

    Place updatePlace(Long pk, String name, String description, String address, Coordinate coordinate) throws Exception;

    void deletePlace(Long pk) throws Exception;
}
