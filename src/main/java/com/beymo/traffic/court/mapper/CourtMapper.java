package com.beymo.traffic.court.mapper;

import com.beymo.traffic.court.dto.CourtRequest;
import com.beymo.traffic.court.dto.CourtResponse;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.station.model.District;
import org.springframework.stereotype.Service;

@Service
public class CourtMapper {
    public CourtResponse toCourtResponse(Court court) {
        return new CourtResponse(
                court.getId(),
                court.getCourtNumber(),
                court.getName(),
                court.getDistrict().getName(),
                court.getDistrict().getProvince().getName()
        );

    }

    public Court toCourt(CourtRequest request, District district) {
        Court court = new Court();
        court.setCourt(request.courtNumber());
        court.setName(request.name());
        court.setDistrict(district);
        return court;
    }

    public Court toCourtUpdate(Court court, CourtRequest request, District district) {
        if(request.courtNumber() != null){
            court.setCourt(request.courtNumber());
        }
        if(district != null){
            court.setDistrict(district);
        }
        if(request.name() != null){
            court.setName(request.name());
        }
        return court;
    }
}
