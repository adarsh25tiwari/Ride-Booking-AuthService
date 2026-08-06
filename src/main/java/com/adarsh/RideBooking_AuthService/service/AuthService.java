package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.dtos.SignupRequestDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupRequestPassengerDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponseDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponsePassengerDto;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;

public interface AuthService {

    public SignupResponsePassengerDto signupP(SignupRequestPassengerDto signupRequestPassengerDto);
    public SignupResponseDriverDto signupD(SignupRequestDriverDto signupRequestDriverDto);
}
