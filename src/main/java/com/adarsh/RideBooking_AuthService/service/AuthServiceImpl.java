package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.dtos.SignupRequestDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupRequestPassengerDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponseDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponsePassengerDto;
import com.adarsh.RideBooking_AuthService.repository.DriverRepository;
import com.adarsh.RideBooking_AuthService.repository.PassengerRepository;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(PassengerRepository passengerRepository, DriverRepository driverRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public SignupResponsePassengerDto signupP(SignupRequestPassengerDto signupRequestPassengerDto) {

        Passenger passenger = new Passenger();
        passenger.setName(signupRequestPassengerDto.getName());
        passenger.setEmail(signupRequestPassengerDto.getEmail());
        passenger.setPassword(bCryptPasswordEncoder.encode(signupRequestPassengerDto.getPassword()));
        passenger.setPhoneNumber(signupRequestPassengerDto.getPhoneNumber());

        Passenger newPassenger = passengerRepository.save(passenger);
        return SignupResponsePassengerDto.fromPassenger(newPassenger);
    }

    @Override
    public SignupResponseDriverDto signupD(SignupRequestDriverDto signupRequestDriverDto) {

        Driver driver = new Driver();
        driver.setName(signupRequestDriverDto.getName());
        driver.setEmail(signupRequestDriverDto.getEmail());
        driver.setPassword(bCryptPasswordEncoder.encode(signupRequestDriverDto.getPassword()));
        driver.setMobileNumber(signupRequestDriverDto.getPhoneNumber());
        driver.setLicenceNumber(signupRequestDriverDto.getLicenceNumber());
        driver.setAadharCard(signupRequestDriverDto.getAadharNumber());

       Driver newDriver =  driverRepository.save(driver);
        return SignupResponseDriverDto.fromDriver(newDriver);
    }
}
