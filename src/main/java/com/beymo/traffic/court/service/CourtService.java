package com.beymo.traffic.court.service;

import com.beymo.traffic.court.dto.CourtRequest;
import com.beymo.traffic.court.dto.CourtResponse;

import java.util.List;

public interface CourtService {
    List<CourtResponse> getAllCourts();

    CourtResponse getCourtById(Long courtId);

    CourtResponse createCourt(CourtRequest request);

    CourtResponse updateCourt(Long courtId, CourtRequest request);

    void deleteById(Long courtId);
}
