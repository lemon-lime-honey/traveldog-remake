package com.llh.traveldog.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.llh.traveldog.data.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
