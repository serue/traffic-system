package com.beymo.traffic.court.repository;

import com.beymo.traffic.court.model.CourtSchedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourtScheduleRepository extends JpaRepository<CourtSchedule, Long> {
    Page<CourtSchedule> findAllByCourtId(Pageable pageable, Long courtId);

    @Query("SELECT cs FROM CourtSchedule cs WHERE :date BETWEEN cs.startDate AND cs.endDate")
    Optional<CourtSchedule> findCourtScheduleInRange(@Param("date") LocalDate date);
}
