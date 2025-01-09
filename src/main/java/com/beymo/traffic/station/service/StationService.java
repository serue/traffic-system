package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.StationRequest;
import com.beymo.traffic.station.dto.StationResponse;

import java.util.List;

public interface StationService {
    List<StationResponse> findAll();

    StationResponse findById(Long stationId);

    StationResponse createStation(StationRequest request);

    StationResponse updateStation(Long stationId, StationRequest request);

    void deleteById(Long stationId);
}
