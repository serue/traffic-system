package com.beymo.traffic.offence.repository;

import com.beymo.traffic.offence.model.Offence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffenceRepository extends JpaRepository<Offence, Long> {
}
