package com.beymo.traffic.court.service;

import com.beymo.traffic.court.dto.ScheduleRequest;
import com.beymo.traffic.court.dto.ScheduleResponse;
import com.beymo.traffic.court.exception.CourtNotFoundException;
import com.beymo.traffic.court.exception.ScheduleNotFoundException;
import com.beymo.traffic.court.mapper.ScheduleMapper;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.court.model.CourtSchedule;
import com.beymo.traffic.court.repository.CourtRepository;
import com.beymo.traffic.court.repository.CourtScheduleRepository;
import com.beymo.traffic.shared.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CourtScheduleServiceImpl implements CourtScheduleService {
    private static final Logger log = Logger.getLogger(CourtScheduleServiceImpl.class.getName());
    private final CourtScheduleRepository scheduleRepository;
    private final CourtRepository courtRepository;
    private final ScheduleMapper mapper;

    public CourtScheduleServiceImpl(CourtScheduleRepository scheduleRepository, CourtRepository courtRepository, ScheduleMapper mapper) {
        this.scheduleRepository = scheduleRepository;
        this.courtRepository = courtRepository;
        this.mapper = mapper;
    }

    @Override
    public PageResponse<ScheduleResponse> getScheduleByCourtId(int page, int size, Long courtId) {
        log.info("getting Schedule By court Id");
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());
        Page<CourtSchedule> schedules = scheduleRepository.findAllByCourtId(pageable, courtId);
        List<ScheduleResponse> scheduleResponse = schedules.stream()
                .map(mapper::toCourtScheduleResponse)
                .toList();
        return new PageResponse<>(
                scheduleResponse,
                schedules.getNumber(),
                schedules.getSize(),
                schedules.getTotalElements(),
                schedules.getTotalPages(),
                schedules.isFirst(),
                schedules.isLast()
        );
    }

    @Override
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        log.info("creating Schedule");
        Court court = courtRepository.findById(request.id())
                .orElseThrow(() -> new CourtNotFoundException("Cannot create a schedule, court is not found"));
        CourtSchedule schedule = scheduleRepository.save(mapper.toSchedule(request, court));
        return mapper.toCourtScheduleResponse(schedule);
    }

    @Override
    public ScheduleResponse updateSchedule(ScheduleRequest request, Long scheduleId) {
        log.info("updating Schedule");
        Court court = courtRepository.findById(request.id())
                .orElseThrow(() -> new CourtNotFoundException("Cannot update a schedule, court is not found"));
        var schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new ScheduleNotFoundException("Court schedule update failed, there is no schedule with the provided id::"+scheduleId));

        CourtSchedule updatedSchedule = scheduleRepository.save(mapper.updateSchedule(request, court, schedule));
        return mapper.toCourtScheduleResponse(updatedSchedule);
    }

    @Override
    public void deleteById(Long courtId) {
        log.info("deleting Schedule");
        scheduleRepository.deleteById(courtId);
    }
}
