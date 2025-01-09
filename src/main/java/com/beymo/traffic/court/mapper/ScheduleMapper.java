package com.beymo.traffic.court.mapper;

import com.beymo.traffic.court.dto.ScheduleRequest;
import com.beymo.traffic.court.dto.ScheduleResponse;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.court.model.CourtSchedule;
import org.springframework.stereotype.Service;

@Service
public class ScheduleMapper {
    public ScheduleResponse toCourtScheduleResponse(CourtSchedule courtSchedule) {
        return new ScheduleResponse(
                courtSchedule.getId(),
                courtSchedule.getCourt().getCourtNumber(),
                courtSchedule.getCourt().getDistrict().getName(),
                courtSchedule.getStartDate(),
                courtSchedule.getEndDate()
        );
    }

    public CourtSchedule toSchedule(ScheduleRequest request, Court court) {
        CourtSchedule courtSchedule = new CourtSchedule();
        courtSchedule.setCourt(court);
        courtSchedule.setStartDate(request.startDate());
        courtSchedule.setEndDate(request.endDate());
        return courtSchedule;
    }

    public CourtSchedule updateSchedule(ScheduleRequest request, Court court, CourtSchedule schedule) {
        if(request.courtId()!=null){
            schedule.setCourt(court);
        }
        if(request.startDate()!=null){
            schedule.setStartDate(request.startDate());
        }
        if(request.endDate()!=null){
            schedule.setEndDate(request.endDate());
        }
        return schedule;
    }
}
