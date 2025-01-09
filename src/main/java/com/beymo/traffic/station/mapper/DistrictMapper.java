package com.beymo.traffic.station.mapper;

import com.beymo.traffic.station.dto.DistrictRequest;
import com.beymo.traffic.station.dto.DistrictResponse;
import com.beymo.traffic.station.model.District;
import com.beymo.traffic.station.model.Province;
import org.springframework.stereotype.Service;

@Service
public class DistrictMapper {
    public DistrictResponse toDistrictResponse(District district) {
        return new DistrictResponse(
                district.getId(),
                district.getCode(),
                district.getName(),
                district.getProvince().getName()
        );
    }

    public District toDistrict(Province province, DistrictRequest request) {
        return new District(
                request.id(),
                request.code(),
                request.name(),
                province
        );
    }

    public District updateDistrict(District district, DistrictRequest request, Province province) {
        if(district.getCode() != null){
            district.setCode(request.code());
        }
        if(district.getName() != null){
            district.setName(request.name());
        }
        if(district.getProvince() != null){
            district.setProvince(province);
        }
        return district;
    }
}
