package com.llh.traveldog.data.dao;

import org.locationtech.jts.geom.Coordinate;

import com.llh.traveldog.data.entity.Place;

public interface PlaceDAO {
    Place insertPlace(Place place);

    Place selectPlace(Long pk);

    Place updatePlace(Long pk, String name, Coordinate coordinate) throws Exception;

    void deletePlace(Long pk) throws Exception;
}
