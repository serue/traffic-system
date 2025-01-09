package com.beymo.traffic.notice.dto;


import com.beymo.traffic.accused.dto.AccusedRequest;
import com.beymo.traffic.accused.dto.LicenceRequest;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.notice.model.NoticeStatus;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.station.model.Station;
import com.beymo.traffic.user.model.User;
import com.beymo.traffic.vehicle.Vehicle;
import com.beymo.traffic.vehicle.dto.VehicleCategoryRequest;
import com.beymo.traffic.vehicle.dto.VehicleRequest;

import java.time.LocalDate;
import java.util.Set;

public record NoticeRequest(
        AccusedRequest accusedRequest,
        LicenceRequest licenceRequest,
        Set<Long> offenceId,
        String crb,
        NoticeStatus status,
        Station station,
        String forceNumber,
        VehicleCategoryRequest categoryRequest,
        VehicleRequest vehicleRequest,
        LocalDate courtDate
) {
}
