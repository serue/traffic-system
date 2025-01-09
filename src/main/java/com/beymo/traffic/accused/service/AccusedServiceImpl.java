package com.beymo.traffic.accused.service;

import com.beymo.traffic.accused.dto.AccusedRequest;
import com.beymo.traffic.accused.dto.AccusedResponse;
import com.beymo.traffic.accused.dto.LicenceRequest;
import com.beymo.traffic.accused.mapper.AccusedMapper;
import com.beymo.traffic.accused.model.Accused;
import com.beymo.traffic.accused.model.Licence;
import com.beymo.traffic.accused.repository.AccusedRepository;
import com.beymo.traffic.accused.repository.LicenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class AccusedServiceImpl implements AccusedService {
    private final AccusedRepository accusedRepository;
    private final LicenceRepository licenceRepository;
    private final AccusedMapper mapper;

    public AccusedServiceImpl(AccusedRepository accusedRepository, LicenceRepository licenceRepository, AccusedMapper mapper) {
        this.accusedRepository = accusedRepository;
        this.licenceRepository = licenceRepository;
        this.mapper = mapper;
    }

    @Override
    public AccusedResponse addAccused(@Validated AccusedRequest request, @Validated LicenceRequest licenceRequest) {
        Accused accused = accusedRepository.save(mapper.toAccused(request));
        Licence license = mapper.toLicence(licenceRequest);
        license.setAccused(accused);
        licenceRepository.save(license);
        return mapper.toAccusedResponse(accused, license);
    }
}
