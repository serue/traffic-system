package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.ProvinceRequest;
import com.beymo.traffic.station.dto.ProvinceResponse;
import com.beymo.traffic.station.exception.ProvinceNotFountException;
import com.beymo.traffic.station.mapper.ProvinceMapper;
import com.beymo.traffic.station.repository.ProvinceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper mapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper mapper) {
        this.provinceRepository = provinceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProvinceResponse> findAll() {
        return provinceRepository.findAll()
                .stream()
                .map(mapper::toProvinceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProvinceResponse createProvince(@Validated ProvinceRequest request) {
        var province = mapper.toProvince(request);
        provinceRepository.save(province);
        return mapper.toProvinceResponse(province);
    }

    @Override
    public ProvinceResponse updateProvince(Long provinceId, ProvinceRequest request) {
        var province = provinceRepository.findById(provinceId)
                .orElseThrow(()-> new ProvinceNotFountException("There is no province with the provided id::"+ provinceId));
        var updatedProvince = mapper.updateProvince(province, request);
        provinceRepository.save(updatedProvince);
        return mapper.toProvinceResponse(updatedProvince);
    }

    @Override
    public void deleteById(Long provinceId) {
        provinceRepository.deleteById(provinceId);
    }

    @Override
    public ProvinceResponse findById(Long provinceId) {
        return provinceRepository.findById(provinceId)
                .map(mapper::toProvinceResponse)
                .orElseThrow(()-> new ProvinceNotFountException("There is no province with the provided id::"+ provinceId));
    }
}
