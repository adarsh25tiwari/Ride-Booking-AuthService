package com.adarsh.RideBooking_AuthService.repository;

import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    Optional<Passenger> findByEmail(String email);
}
