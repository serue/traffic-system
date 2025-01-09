package com.beymo.traffic.vehicle.repository;

import com.beymo.traffic.vehicle.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCategoryRepository extends JpaRepository<Category, Long> {
}
