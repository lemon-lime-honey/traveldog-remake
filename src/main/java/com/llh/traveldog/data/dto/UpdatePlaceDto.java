package com.llh.traveldog.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.locationtech.jts.geom.Coordinate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.llh.traveldog.data.CoordinateDeserializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePlaceDto {
    private Long pk;
    private String name;
    private String description;
    private String address;

    @JsonDeserialize(using = CoordinateDeserializer.class)
    private Coordinate coordinate;
}