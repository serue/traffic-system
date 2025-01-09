package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.ProvinceRequest;
import com.beymo.traffic.station.dto.ProvinceResponse;

import java.util.List;

public interface ProvinceService {
    List<ProvinceResponse> findAll();

    ProvinceResponse createProvince(ProvinceRequest request);

    ProvinceResponse updateProvince(Long provinceId, ProvinceRequest request);

    void deleteById(Long provinceId);

    ProvinceResponse findById(Long provinceId);
}
