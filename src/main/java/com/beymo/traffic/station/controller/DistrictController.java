package com.beymo.traffic.station.controller;

import com.beymo.traffic.station.dto.DistrictRequest;
import com.beymo.traffic.station.dto.DistrictResponse;
import com.beymo.traffic.station.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<DistrictResponse>> getDistricts(){
        return ResponseEntity.ok(districtService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{district-id}")
    public ResponseEntity<DistrictResponse> getDistrictById(@PathVariable("district-id") Long districtId){
        return ResponseEntity.ok(districtService.findById(districtId));
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<DistrictResponse> createDistrict(@RequestBody @Validated DistrictRequest request){
        var districtResponse = districtService.createDistrict(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(districtResponse);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/{district-id}")
    public ResponseEntity<DistrictResponse>  updateDistrict(@PathVariable("district-id") Long districtId, @RequestBody @Validated DistrictRequest request){
        var districtResponse = districtService.updateDistrict(districtId,request);
        return ResponseEntity.status(HttpStatus.OK).body(districtResponse);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @DeleteMapping("/{district-id}")
    public ResponseEntity<String> deleteDistrict(@PathVariable("district-id") Long districtId){
        districtService.deleteById(districtId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("District was deleted successfully");
    }
}
