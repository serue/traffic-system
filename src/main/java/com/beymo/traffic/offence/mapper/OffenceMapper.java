package com.beymo.traffic.offence.mapper;

import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;
import com.beymo.traffic.offence.model.Offence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OffenceMapper {
    public OffenceResponse toOffenceResponse(Offence offence) {
        return new OffenceResponse(
                offence.getId(),
                offence.getSection(),
                offence.getTitle(),
                offence.getFine()
        );
    }

    public Offence toOffence(OffenceRequest request) {
        return new Offence(
                request.id(),
                request.section(),
                request.title(),
                request.fine()
        );
    }

    public Offence updateOffence(Offence existingOffence, OffenceRequest updateRequest) {
        if (updateRequest.section() != null) {
            existingOffence.setSection(updateRequest.section());
        }
        if (updateRequest.title() != null) {
            existingOffence.setTitle(updateRequest.title());
        }
        if(updateRequest.fine() != null){
            existingOffence.setFine(updateRequest.fine());
        }
        // Add more fields here as needed
        return existingOffence;
    }


}
