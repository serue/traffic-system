package com.beymo.traffic.court.controller;

import com.beymo.traffic.court.dto.ScheduleRequest;
import com.beymo.traffic.court.dto.ScheduleResponse;
import com.beymo.traffic.court.service.CourtScheduleService;
import com.beymo.traffic.shared.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/court-schedules")
public class CourtScheduleController {

    private final CourtScheduleService courtScheduleService;

    public CourtScheduleController(CourtScheduleService courtScheduleService) {
        this.courtScheduleService = courtScheduleService;
    }
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{court-id}")
    public ResponseEntity<PageResponse<ScheduleResponse>> getAllSchedulesByCourt(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("court-id") Long courtId) {
        return ResponseEntity.ok(courtScheduleService.getScheduleByCourtId(page, size, courtId));
    }
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody @Validated ScheduleRequest request) {
        ScheduleResponse response = courtScheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/{schedule-id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@RequestBody @Validated ScheduleRequest request,
                                                           @PathVariable("schedule-id") Long scheduleId) {

        ScheduleResponse response = courtScheduleService.updateSchedule(request, scheduleId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{court-id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("court-id") Long courtId) {
        courtScheduleService.deleteById(courtId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Schedule was deleted successfully");
    }

    // todo: create an endpoint for getting the scheduled court for a given period
}
