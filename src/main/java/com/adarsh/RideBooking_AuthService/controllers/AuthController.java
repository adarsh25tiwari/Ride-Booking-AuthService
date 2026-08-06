package com.adarsh.RideBooking_AuthService.controllers;

import com.adarsh.RideBooking_AuthService.dtos.SignupRequestDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupRequestPassengerDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponseDriverDto;
import com.adarsh.RideBooking_AuthService.dtos.SignupResponsePassengerDto;
import com.adarsh.RideBooking_AuthService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    public  AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/passenger")
    public ResponseEntity<SignupResponsePassengerDto> signupPassenger(@RequestBody SignupRequestPassengerDto signupRequestPassengerDto) {
       SignupResponsePassengerDto response =  this.authService.signupP(signupRequestPassengerDto);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/driver")
    public ResponseEntity<SignupResponseDriverDto> signupDriver(@RequestBody SignupRequestDriverDto signupRequestDriverDto) {
        SignupResponseDriverDto response = this.authService.signupD(signupRequestDriverDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
