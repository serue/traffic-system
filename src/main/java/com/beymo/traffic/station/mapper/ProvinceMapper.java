package com.beymo.traffic.station.mapper;

import com.beymo.traffic.station.dto.ProvinceRequest;
import com.beymo.traffic.station.dto.ProvinceResponse;
import com.beymo.traffic.station.model.Province;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service   
public class ProvinceMapper {
    public ProvinceResponse toProvinceResponse(Province province) {
        return new ProvinceResponse(
                province.getId(),
                province.getCode(),
                province.getName()
        );
    }

    public Province toProvince(ProvinceRequest request) {
        return new Province(
                request.id(),
                request.code(),
                request.name()
        );
    }

    public Province updateProvince(Province province, ProvinceRequest request) {
        if(request.id() != null) {
            province.setId(request.id());
        }
        if(request.code() != null) {
            province.setCode(request.code());
        }
        if(request.name() != null) {
            province.setName(request.name());
        }
        return province;
    }
}
