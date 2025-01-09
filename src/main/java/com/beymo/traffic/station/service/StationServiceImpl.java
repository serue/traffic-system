package com.beymo.traffic.station.service;

import com.beymo.traffic.station.dto.StationRequest;
import com.beymo.traffic.station.dto.StationResponse;
import com.beymo.traffic.station.exception.DistrictNotFoundException;
import com.beymo.traffic.station.exception.StationNotFoundException;
import com.beymo.traffic.station.mapper.StationMapper;
import com.beymo.traffic.station.repository.DistrictRepository;
import com.beymo.traffic.station.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper mapper;
    private final DistrictRepository districtRepository;


    public StationServiceImpl(StationRepository stationRepository, StationMapper mapper, DistrictRepository districtRepository) {
        this.stationRepository = stationRepository;
        this.mapper = mapper;
        this.districtRepository = districtRepository;
    }

    @Override
    public List<StationResponse> findAll() {
        return stationRepository.findAll()
                .stream()
                .map(mapper::toStationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StationResponse findById(Long stationId) {
        return stationRepository.findById(stationId)
                .map(mapper::toStationResponse)
                .orElseThrow(()-> new StationNotFoundException("No station found with the provided id::"+stationId));
    }

    @Override
    public StationResponse createStation(StationRequest request) {
        var district = districtRepository.findById(request.districtId())
                .orElseThrow(()-> new DistrictNotFoundException("Fail to create station because the district is non existent"));
        var station = stationRepository.save(mapper.toStation(district, request));
        return mapper.toStationResponse(station);
    }

    @Override
    public StationResponse updateStation(Long stationId, StationRequest request) {
        var district = districtRepository.findById(request.districtId())
                .orElseThrow(()-> new DistrictNotFoundException("Fail to update station because the district is non existent"));
        var station = stationRepository.findById(request.districtId())
                .orElseThrow(()-> new DistrictNotFoundException("Fail to update station because the station is non existent"));
        var updatedStation = stationRepository.save(mapper.updateStation(district,station, request));
        return mapper.toStationResponse(updatedStation);
    }

    @Override
    public void deleteById(Long stationId) {
        stationRepository.deleteById(stationId);
    }
}
