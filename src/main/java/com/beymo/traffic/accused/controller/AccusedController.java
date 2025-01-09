package com.beymo.traffic.accused.controller;

import com.beymo.traffic.accused.dto.AccusedRequest;
import com.beymo.traffic.accused.dto.AccusedResponse;
import com.beymo.traffic.accused.dto.LicenceRequest;
import com.beymo.traffic.accused.service.AccusedService;
import com.beymo.traffic.accused.service.LicenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accused")
public class AccusedController {

    private final AccusedService accusedService;


    public AccusedController(AccusedService accusedService) {
        this.accusedService = accusedService;

    }

    @PostMapping
    public ResponseEntity<AccusedResponse> createAccused(@RequestBody @Validated AccusedRequest accusedRequest, @RequestBody @Validated LicenceRequest licenceRequest) {
        var response = accusedService.addAccused(accusedRequest, licenceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
