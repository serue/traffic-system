package com.beymo.traffic.station.controller;

import com.beymo.traffic.station.dto.ProvinceRequest;
import com.beymo.traffic.station.dto.ProvinceResponse;
import com.beymo.traffic.station.service.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<ProvinceResponse>> getProvinces(){
        return ResponseEntity.ok(provinceService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<ProvinceResponse> addProvince(@RequestBody @Validated ProvinceRequest request){
        var provinceResponse = provinceService.createProvince(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceResponse);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/{province-id}")
    public ResponseEntity<ProvinceResponse> updateProvince(
            @PathVariable("province-id") Long provinceId, @RequestBody @Validated ProvinceRequest request){
        var provinceResponse = provinceService.updateProvince(provinceId, request);
        return ResponseEntity.status(HttpStatus.OK).body(provinceResponse);

    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @DeleteMapping("/{province-id}")
    public ResponseEntity<String> deleteProvince(@PathVariable("province-id") Long provinceId){
        provinceService.deleteById(provinceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The province is successfully deleted");
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{province-id}")
    public ResponseEntity<ProvinceResponse> getProvinceById(@PathVariable("province-id") Long provinceId){
        var provinceResponse = provinceService.findById(provinceId);
        return ResponseEntity.status(HttpStatus.OK).body(provinceResponse);
    }
}
