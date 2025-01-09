package com.beymo.traffic.offence.service;

import com.beymo.traffic.offence.dto.FineRequest;
import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;

import java.util.List;

public interface OffenceService {
    List<OffenceResponse> findAll();

    OffenceResponse findById(Long offenceId);

    void deleteOffenceById(Long offenceId);

    OffenceResponse createOffence(OffenceRequest request);

    OffenceResponse updateOffence(Long offenceId, OffenceRequest updatedOffence);


    OffenceResponse updateFine(Long offenceId, FineRequest request);
}
