package com.beymo.traffic.notice.service;

import com.beymo.traffic.accused.model.Accused;
import com.beymo.traffic.accused.model.Licence;
import com.beymo.traffic.accused.repository.AccusedRepository;
import com.beymo.traffic.accused.repository.LicenceRepository;
import com.beymo.traffic.court.exception.CourtNotFoundException;
import com.beymo.traffic.court.model.CourtSchedule;
import com.beymo.traffic.court.repository.CourtScheduleRepository;
import com.beymo.traffic.notice.dto.NoticeRequest;
import com.beymo.traffic.notice.model.Notice;
import com.beymo.traffic.notice.model.NoticeStatus;
import com.beymo.traffic.notice.repository.NoticeRepository;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.offence.repository.OffenceRepository;
import com.beymo.traffic.user.exception.UserNotFoundException;
import com.beymo.traffic.user.model.User;
import com.beymo.traffic.user.repository.UserRepository;
import com.beymo.traffic.vehicle.Category;
import com.beymo.traffic.vehicle.Vehicle;
import com.beymo.traffic.vehicle.repository.VehicleCategoryRepository;
import com.beymo.traffic.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final CourtScheduleRepository courtRepository;
    private final LicenceRepository licenceRepository;
    private final AccusedRepository accusedRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final UserRepository userRepository;
    private final OffenceRepository offenceRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, CourtScheduleRepository  courtRepository, LicenceRepository licenceRepository, AccusedRepository accusedRepository, VehicleRepository vehicleRepository, VehicleCategoryRepository vehicleCategoryRepository, UserRepository userRepository, OffenceRepository offenceRepository) {
        this.noticeRepository = noticeRepository;
        this.courtRepository = courtRepository;
        this.licenceRepository = licenceRepository;
        this.accusedRepository = accusedRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleCategoryRepository = vehicleCategoryRepository;
        this.userRepository = userRepository;
        this.offenceRepository = offenceRepository;
    }


    @Override
    @Transactional
    public String createNotice(NoticeRequest request) {
        // todo court
        CourtSchedule schedule = courtRepository.findCourtScheduleInRange(request.courtDate())
                .orElseThrow(() -> new CourtNotFoundException("There is not court for the provided dates therefore:: you cannot issue a form 265, please find a different court date"));

        //todo Accused
        Accused accused = new Accused();
        accused.setFirstname(request.accusedRequest().firstname());
        accused.setLastname(request.accusedRequest().lastname());
        accused.setEmail(request.accusedRequest().email());
        accused.setPhone(request.accusedRequest().phone());
        accused.setAddress(request.accusedRequest().address());
        accused.setBusinessAddress(request.accusedRequest().businessAddress());
        accused.setCity(request.accusedRequest().city());
        accused.setBirthplace(request.accusedRequest().birthplace());
        accused.setCountry(request.accusedRequest().country());
        accused.setGender(request.accusedRequest().gender());
        accused.setDob(request.accusedRequest().dob());
        accused.setEntryDate(request.accusedRequest().entryDate());
        accused.setPort(request.accusedRequest().port());
        accusedRepository.save(accused);

        // todo offence
        Set<Offence> offences = new HashSet<>(offenceRepository.findAllById(request.offenceId()));

        //todo licenses
        Licence licence = new Licence();
        licence.setNumber(request.licenceRequest().number());
        licence.setFirstIssueDate(request.licenceRequest().firstIssueDate());
        licence.setIssueDate(request.licenceRequest().issueDate());
        licence.setExpiryDate(request.licenceRequest().expiryDate());
        licence.setAccused(accused);
        licenceRepository.save(licence);

        //todo vehicle category
        Category category = new Category();
        category.setName(request.categoryRequest().name());
        vehicleCategoryRepository.save(category);

        //todo vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setCategory(category);
        vehicle.setRegistration(request.vehicleRequest().registration());
        vehicle.setModel(request.vehicleRequest().model());
        vehicle.setColor(request.vehicleRequest().color());
        vehicleRepository.save(vehicle);

        // todo issuer
        User issuer = userRepository.findByForcenumber(request.forceNumber())
                .orElseThrow(()-> new UserNotFoundException("The form 265 cannot be created because there is no issuer"));

        Notice notice = new Notice();
        notice.setAccused(accused);
        notice.setCourt(schedule.getCourt());
        notice.setIssuedBy(issuer);
        notice.setStation(issuer.getStation());
        notice.setVehicle(vehicle);
        notice.setStatus(NoticeStatus.PAYMENT_PENDING);
        noticeRepository.save(notice);
        return "Notice was created successfully";
    }
}
