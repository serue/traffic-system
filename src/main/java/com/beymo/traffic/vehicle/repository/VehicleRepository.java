package com.beymo.traffic.vehicle.repository;

import com.beymo.traffic.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {
}
