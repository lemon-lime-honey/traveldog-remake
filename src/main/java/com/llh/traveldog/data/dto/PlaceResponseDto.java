package com.llh.traveldog.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.Coordinate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceResponseDto {
    private Long pk;
    private String name;
    private Coordinate coordinate;
}
