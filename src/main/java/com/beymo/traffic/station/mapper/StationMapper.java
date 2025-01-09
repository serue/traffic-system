package com.beymo.traffic.station.mapper;

import com.beymo.traffic.station.dto.StationRequest;
import com.beymo.traffic.station.dto.StationResponse;
import com.beymo.traffic.station.model.District;
import com.beymo.traffic.station.model.Station;
import org.springframework.stereotype.Service;

@Service
public class StationMapper {
    public StationResponse toStationResponse(Station station) {
        return new StationResponse(
                station.getId(),
                station.getCode(),
                station.getName(),
                station.getDistrict().getName(),
                station.getDistrict().getProvince().getName()
        );
    }

    public Station toStation(District district, StationRequest request) {
        return new Station(
                request.id(),
                request.code(),
                request.name(),
                district
        );
    }

    public Station updateStation(District district, Station station, StationRequest request) {
        if(station.getCode() != null){
            station.setCode(request.code());
        }
        if(station.getName() != null){
            station.setName(request.name());
        }
        if(station.getDistrict() != null){
            station.setDistrict(district);
        }
        return station;
    }
}
