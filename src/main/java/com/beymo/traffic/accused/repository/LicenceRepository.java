package com.beymo.traffic.accused.repository;

import com.beymo.traffic.accused.model.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Long> {
}
