package com.llh.traveldog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PlaceResponseDto>> getPlaceList() {
        List<PlaceResponseDto> placeResponseDtos = placeService.getPlaceList();
        return ResponseEntity.status(HttpStatus.OK).body(placeResponseDtos);
    }

    @GetMapping("/{pk}")
    @Operation(summary = "Get Place Information", description = "개별 장소 조회")
    public ResponseEntity<PlaceResponseDto> getPlace(@PathVariable Long pk) {
        PlaceResponseDto placeResponseDto = placeService.getPlace(pk);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponseDto);
    }

    @PostMapping
    public ResponseEntity<PlaceResponseDto> createPlace(@RequestBody PlaceDto placeDto) {
        PlaceResponseDto placeResponseDto = placeService.savePlace(placeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponseDto);
    }

    @PutMapping
    public ResponseEntity<PlaceResponseDto> updatePlace(
        @RequestBody UpdatePlaceDto updatePlaceDto) throws Exception {
        PlaceResponseDto placeResponseDto = placeService.updatePlace(updatePlaceDto);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePlace(Long pk) throws Exception {
        placeService.deletePlace(pk);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("정상적으로 삭제되었습니다.");
    }
}
