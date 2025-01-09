package com.beymo.traffic.station.controller;

import com.beymo.traffic.station.dto.StationRequest;
import com.beymo.traffic.station.dto.StationResponse;
import com.beymo.traffic.station.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<StationResponse>> getStations(){
        return ResponseEntity.ok(stationService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{station-id}")
    public ResponseEntity<StationResponse> getStationById(@PathVariable("station-id") Long stationId){
        return ResponseEntity.ok(stationService.findById(stationId));
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<StationResponse> createStation(@RequestBody @Validated StationRequest request){
        var stationResponse = stationService.createStation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationResponse);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/{station-id}")
    public ResponseEntity<StationResponse> updateStation(@PathVariable("station-id") Long stationId, @RequestBody StationRequest request){
        var stationResponse = stationService.updateStation(stationId, request);
        return ResponseEntity.ok(stationResponse);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @DeleteMapping("/{station-id}")
    public ResponseEntity<String> deleteStation(@PathVariable("station-id") Long stationId){
        stationService.deleteById(stationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Station successfully deleted");
    }
}
