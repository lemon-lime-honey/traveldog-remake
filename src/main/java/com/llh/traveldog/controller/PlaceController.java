package com.llh.traveldog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.llh.traveldog.data.dto.PlaceDto;
import com.llh.traveldog.data.dto.PlaceResponseDto;
import com.llh.traveldog.data.dto.UpdatePlaceDto;
import com.llh.traveldog.service.PlaceService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/place")
@Tag(name = "Place", description = "Place API")
public class PlaceController {
    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public CollectionModel<EntityModel<PlaceResponseDto>> getPlaceList() {
        List<EntityModel<PlaceResponseDto>> placeResponseDtos = placeService.getPlaceList().stream()
            .map(place -> EntityModel.of(place,
                linkTo(methodOn(PlaceController.class).getPlace(place.getPk())).withSelfRel()))
            .collect(Collectors.toList());
        return CollectionModel.of(placeResponseDtos,
            linkTo(methodOn(PlaceController.class).getPlaceList()).withSelfRel());
    }

    @GetMapping("/{pk}")
    @Operation(summary = "Get Place Information", description = "개별 장소 조회")
    public EntityModel<PlaceResponseDto> getPlace(@PathVariable Long pk) {
        PlaceResponseDto placeResponseDto = placeService.getPlace(pk);
        return EntityModel.of(placeResponseDto,
            linkTo(methodOn(PlaceController.class).getPlace(pk)).withSelfRel(),
            linkTo(methodOn(PlaceController.class).getPlaceList()).withRel("place"));
    }

    @PostMapping
    public EntityModel<PlaceResponseDto> createPlace(@RequestBody PlaceDto placeDto) {
        PlaceResponseDto placeResponseDto = placeService.savePlace(placeDto);
        return EntityModel.of(placeResponseDto,
            linkTo(methodOn(PlaceController.class).getPlace(placeResponseDto.getPk())).withSelfRel(),
            linkTo(methodOn(PlaceController.class).getPlaceList()).withRel("place"));
    }

    @PutMapping("/{pk}")
    public EntityModel<PlaceResponseDto> updatePlace(
        @RequestBody UpdatePlaceDto updatePlaceDto) throws Exception {
        PlaceResponseDto placeResponseDto = placeService.updatePlace(updatePlaceDto);
        return EntityModel.of(placeResponseDto,
            linkTo(methodOn(PlaceController.class).getPlace(placeResponseDto.getPk())).withSelfRel(),
            linkTo(methodOn(PlaceController.class).getPlaceList()).withRel("place"));
    }

    @DeleteMapping("/{pk}")
    public void deletePlace(@PathVariable Long pk) throws Exception {
        placeService.deletePlace(pk);
    }
}
