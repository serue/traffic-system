package com.beymo.traffic.offence.controller;

import com.beymo.traffic.offence.dto.FineRequest;
import com.beymo.traffic.offence.dto.OffenceRequest;
import com.beymo.traffic.offence.dto.OffenceResponse;
import com.beymo.traffic.offence.service.OffenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offences")
public class OffenceController {

    private final OffenceService offenceService;

    public OffenceController(OffenceService offenceService) {
        this.offenceService = offenceService;
    }

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OffenceResponse>> getOffences() {
        return ResponseEntity.ok(offenceService.findAll());
    }

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OffenceResponse> createOffence(@RequestBody @Validated OffenceRequest offenceRequest) {
        OffenceResponse offenceResponse = offenceService.createOffence(offenceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(offenceResponse);
    }

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping("/{offence-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OffenceResponse> getOffenceById(@PathVariable("offence-id") Long offenceId) {
        return ResponseEntity.ok(offenceService.findById(offenceId));
    }

    @PreAuthorize("hasRole('STAFF')")
    @PutMapping("/{offence-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OffenceResponse> updateOffence(
            @PathVariable("offence-id") Long offenceId,
            @RequestBody @Validated OffenceRequest offenceRequest) {
        OffenceResponse updatedOffence = offenceService.updateOffence(offenceId, offenceRequest);
        return ResponseEntity.ok(updatedOffence);
    }

    @PreAuthorize("hasRole('STAFF')")
    @PatchMapping("/{offence-id}/fine")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OffenceResponse> updateOffenceFine(
            @PathVariable("offence-id") Long offenceId,
            @RequestBody @Validated FineRequest fineRequest) {
        OffenceResponse updatedOffence = offenceService.updateFine(offenceId, fineRequest);
        return ResponseEntity.ok(updatedOffence);
    }

    @PreAuthorize("hasRole('STAFF')")
    @DeleteMapping("/{offence-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OffenceResponse> deleteOffence(
            @PathVariable("offence-id") Long offenceId) {
        offenceService.deleteOffenceById(offenceId);
        return ResponseEntity.ok().build();
    }
}
