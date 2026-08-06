package com.adarsh.RideBooking_AuthService.repository;

import com.adarsh.RideBooking_EntityService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
}
