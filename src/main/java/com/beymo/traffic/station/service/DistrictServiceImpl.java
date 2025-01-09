package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.DistrictRequest;
import com.beymo.traffic.station.dto.DistrictResponse;
import com.beymo.traffic.station.exception.DistrictNotFoundException;
import com.beymo.traffic.station.exception.ProvinceNotFountException;
import com.beymo.traffic.station.mapper.DistrictMapper;
import com.beymo.traffic.station.repository.DistrictRepository;
import com.beymo.traffic.station.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper mapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ProvinceRepository provinceRepository, DistrictMapper mapper) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DistrictResponse> findAll() {
        return districtRepository.findAll()
                .stream()
                .map(mapper::toDistrictResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictResponse findById(Long districtId) {
        return districtRepository.findById(districtId)
                .map(mapper::toDistrictResponse)
                .orElseThrow(()-> new DistrictNotFoundException("District not found"));
    }

    @Override
    public DistrictResponse createDistrict(DistrictRequest request) {
        var province =provinceRepository.findById(request.provinceId())
                .orElseThrow(()-> new ProvinceNotFountException("Failed to create a new district, the selected province does not exist"));
        var district = mapper.toDistrict(province, request);
        districtRepository.save(district);
        return mapper.toDistrictResponse(district);
    }

    @Override
    public DistrictResponse updateDistrict(Long districtId, DistrictRequest request) {
        var district = districtRepository.findById(districtId)
                .orElseThrow(()-> new DistrictNotFoundException("No district is found with the provided id"));
        var province =provinceRepository.findById(request.provinceId())
                .orElseThrow(()-> new ProvinceNotFountException("Failed to create a new district, the selected province does not exist"));
        var updatedDistrict = mapper.updateDistrict(district, request, province);
        districtRepository.save(updatedDistrict);
        return mapper.toDistrictResponse(updatedDistrict);
    }

    @Override
    public void deleteById(Long districtId) {
        districtRepository.deleteById(districtId);
    }


}
