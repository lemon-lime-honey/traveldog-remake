package com.llh.traveldog.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.Coordinate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceDto {
    private String name;
    private String description;
    private String address;
    private Coordinate coordinate;
}
