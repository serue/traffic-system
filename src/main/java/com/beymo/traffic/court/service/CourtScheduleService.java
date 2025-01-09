package com.beymo.traffic.court.service;

import com.beymo.traffic.court.dto.ScheduleRequest;
import com.beymo.traffic.court.dto.ScheduleResponse;
import com.beymo.traffic.shared.PageResponse;

import java.util.List;

public interface CourtScheduleService {

    PageResponse<ScheduleResponse> getScheduleByCourtId(int page, int size, Long courtId);

    ScheduleResponse createSchedule(ScheduleRequest request);

    ScheduleResponse updateSchedule(ScheduleRequest request, Long scheduleId);

    void deleteById(Long courtId);

}
