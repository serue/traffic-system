package com.beymo.traffic.offence.service;

import com.beymo.traffic.offence.dto.FineRequest;
import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;
import com.beymo.traffic.offence.exception.OffenceNotFoundException;
import com.beymo.traffic.offence.mapper.OffenceMapper;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.offence.repository.OffenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class OffenceServiceImpl  implements OffenceService {

    private static final Logger log = Logger.getLogger(OffenceServiceImpl.class.getName());
    private final OffenceRepository offenceRepository;
    private final OffenceMapper mapper;

    public OffenceServiceImpl(OffenceRepository offenceRepository, OffenceMapper mapper) {
        this.offenceRepository = offenceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OffenceResponse> findAll() {
        log.info("Finding all offences");
        return offenceRepository.findAll()
                .stream()
                .map(mapper::toOffenceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OffenceResponse findById(Long offenceId) {
        return offenceRepository.findById(offenceId)
                .map(mapper::toOffenceResponse)
                .orElseThrow(() -> new OffenceNotFoundException("No offence found with the provided id::"+ offenceId));
    }

    @Override
    public void deleteOffenceById(Long offenceId) {
        offenceRepository.deleteById(offenceId);
    }

    @Override
    public OffenceResponse createOffence(@Validated OffenceRequest request) {
        log.info("Creating an offence");
        var offence = offenceRepository.save(mapper.toOffence(request));
        return mapper.toOffenceResponse(offence);
    }

    @Override
    public OffenceResponse updateOffence(Long offenceId, OffenceRequest request) {
        var offence = offenceRepository.findById(offenceId)
                .orElseThrow(() -> new OffenceNotFoundException("update fail, no offence found with the provided id::"+ offenceId));
        var updateOffence = offenceRepository.save(mapper.updateOffence(offence, request));
        return mapper.toOffenceResponse(updateOffence);
    }

    @Override
    public OffenceResponse updateFine(Long offenceId, FineRequest request) {
        Offence offence = offenceRepository.findById(offenceId)
                .orElseThrow(() -> new OffenceNotFoundException("No offence found with ID: " + request.id()));
        offence.setFine(request.fine());
        Offence updatedOffence = offenceRepository.save(offence);
        return mapper.toOffenceResponse(updatedOffence);
    }


}
