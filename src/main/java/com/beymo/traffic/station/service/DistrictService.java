package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.DistrictRequest;
import com.beymo.traffic.station.dto.DistrictResponse;

import java.util.List;

public interface DistrictService {
    List<DistrictResponse> findAll();

    DistrictResponse findById(Long districtId);

    DistrictResponse createDistrict(DistrictRequest request);

    DistrictResponse updateDistrict(Long districtId, DistrictRequest request);

    void deleteById(Long districtId);
}
