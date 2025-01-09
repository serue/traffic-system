package com.beymo.traffic.accused.mapper;

import com.beymo.traffic.accused.dto.AccusedRequest;
import com.beymo.traffic.accused.dto.AccusedResponse;
import com.beymo.traffic.accused.dto.LicenceRequest;
import com.beymo.traffic.accused.model.Accused;
import com.beymo.traffic.accused.model.Licence;
import org.springframework.stereotype.Service;

@Service
public class AccusedMapper {
    public Accused toAccused(AccusedRequest request) {
        Accused accused = new Accused();
        accused.setFirstname(request.firstname());
        accused.setLastname(request.lastname());
        accused.setEmail(request.email());
        accused.setPhone(request.phone());
        accused.setAddress(request.address());
        accused.setBusinessAddress(request.businessAddress());
        accused.setCity(request.city());
        accused.setBirthplace(request.birthplace());
        accused.setCountry(request.country());
        accused.setGender(request.gender());
        accused.setDob(request.dob());
        accused.setEntryDate(request.entryDate());
        accused.setPort(request.port());
        return accused;
    }

    public Licence toLicence(LicenceRequest request) {
        Licence licence = new Licence();
        licence.setNumber(request.number());
        licence.setFirstIssueDate(request.firstIssueDate());
        licence.setIssueDate(request.issueDate());
        licence.setExpiryDate(request.expiryDate());
        return licence;
    }

    public AccusedResponse toAccusedResponse(Accused accused, Licence license) {
        return new AccusedResponse(
                accused.getId(),
                accused.getFirstname(),
                accused.getLastname(),
                accused.getEmail(),
                accused.getPhone(),
                accused.getAddress(),
                accused.getBusinessAddress(),
                accused.getCity(),
                accused.getBirthplace(),
                accused.getDob(),
                accused.getGender(),
                accused.getCountry(),
                accused.getEntryDate(),
                accused.getPort(),
                license.getNumber(),
                license.getFirstIssueDate(),
                license.getIssueDate(),
                license.getExpiryDate(),
                license.getPlaceOfIssue()
        );
    }
}
