package com.adarsh.RideBooking_AuthService.repository;

import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
