package com.beymo.traffic.court.controller;

import com.beymo.traffic.court.dto.CourtRequest;
import com.beymo.traffic.court.dto.CourtResponse;
import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.court.service.CourtService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {
    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<CourtResponse>> getAllCourts() {
        return ResponseEntity.ok(courtService.getAllCourts());
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{court-id}")
    public ResponseEntity<CourtResponse> getCourtById(@PathVariable("court-id") Long courtId) {
        return  ResponseEntity.ok(courtService.getCourtById(courtId));
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<CourtResponse> createCourt(@RequestBody @Validated CourtRequest request) {
        var response = courtService.createCourt(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/{court-id}")
    public ResponseEntity<CourtResponse> updateCourt(@RequestBody @Validated CourtRequest request,
                                                     @PathVariable("court-id") Long courtId) {
        CourtResponse response = courtService.updateCourt(courtId, request);
        return ResponseEntity.accepted().body(response);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @DeleteMapping("/{court-id}")
    public ResponseEntity<String> deleteCourt(@PathVariable("court-id") Long courtId) {
        courtService.deleteById(courtId);
        return ResponseEntity.status(HttpStatus.OK).body("Court was deleted successfully");
    }
}
