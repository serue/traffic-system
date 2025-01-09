package com.beymo.traffic.court.service;

import com.beymo.traffic.court.dto.CourtRequest;
import com.beymo.traffic.court.dto.CourtResponse;
import com.beymo.traffic.court.exception.CourtNotFoundException;
import com.beymo.traffic.court.mapper.CourtMapper;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.court.repository.CourtRepository;
import com.beymo.traffic.station.exception.DistrictNotFoundException;
import com.beymo.traffic.station.model.District;
import com.beymo.traffic.station.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CourtServiceImpl implements CourtService {
    private static final Logger log = Logger.getLogger(CourtServiceImpl.class.getName());
    private final CourtRepository courtRepository;
    private final CourtMapper mapper;
    private final DistrictRepository districtRepository;

    public CourtServiceImpl(CourtRepository courtRepository, CourtMapper mapper, DistrictRepository districtRepository) {
        this.courtRepository = courtRepository;
        this.mapper = mapper;
        this.districtRepository = districtRepository;
    }

    @Override
    public List<CourtResponse> getAllCourts() {
        log.info("Getting list of all courts");
        return courtRepository.findAll()
                .stream()
                .map(mapper::toCourtResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourtResponse getCourtById(Long courtId) {
        var court = courtRepository.findById(courtId)
                .orElseThrow(()-> new CourtNotFoundException("No court found with the provided id::"+courtId));
        return mapper.toCourtResponse(court);
    }

    @Override
    public CourtResponse createCourt(CourtRequest request) {
        District district = districtRepository.findById(request.districtId())
                .orElseThrow(()-> new DistrictNotFoundException("Cannot create a court in a non existent district::"+request.districtId()));
        Court court = courtRepository.save(mapper.toCourt(request, district));
        return mapper.toCourtResponse(court);
    }

    @Override
    public CourtResponse updateCourt(Long courtId, CourtRequest request) {
        District district = districtRepository.findById(request.districtId())
                .orElseThrow(()-> new DistrictNotFoundException("Cannot create a court in a non existent district::"+request.districtId()));
        Court court = courtRepository.findById(courtId)
                .orElseThrow(()-> new CourtNotFoundException("Update failed,, court found with the provided id::"+courtId));
        Court updatedCourt = mapper.toCourtUpdate(court, request, district);
        return mapper.toCourtResponse(updatedCourt);
    }

    @Override
    public void deleteById(Long courtId) {
        courtRepository.deleteById(courtId);
    }
}
