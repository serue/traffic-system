package com.beymo.traffic.accused.service;

import com.beymo.traffic.accused.dto.AccusedRequest;
import com.beymo.traffic.accused.dto.AccusedResponse;
import com.beymo.traffic.accused.dto.LicenceRequest;

public interface AccusedService {
    AccusedResponse addAccused(AccusedRequest request, LicenceRequest licenceRequest);
}
