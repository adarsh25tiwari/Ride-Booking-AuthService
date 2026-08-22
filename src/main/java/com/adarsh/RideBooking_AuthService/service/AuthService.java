package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.dtos.*;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;

public interface AuthService {

    public SignupResponsePassengerDto signupP(SignupRequestPassengerDto signupRequestPassengerDto);
    public SignupResponseDriverDto signupD(SignupRequestDriverDto signupRequestDriverDto);
    public AuthResponseDto login(AuthRequestDto authRequestDto);
    TokenValidationResponseDto validateToken(String token);
}
