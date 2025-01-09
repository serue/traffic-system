package com.beymo.traffic.accused.repository;

import com.beymo.traffic.accused.model.Accused;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccusedRepository extends JpaRepository<Accused, Long> {
}
