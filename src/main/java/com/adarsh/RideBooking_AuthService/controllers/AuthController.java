package com.adarsh.RideBooking_AuthService.controllers;

import com.adarsh.RideBooking_AuthService.dtos.*;
import com.adarsh.RideBooking_AuthService.service.AuthService;
import com.adarsh.RideBooking_AuthService.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public  AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<SignupResponsePassengerDto> signupPassenger(@RequestBody SignupRequestPassengerDto signupRequestPassengerDto) {
       SignupResponsePassengerDto response =  this.authService.signupP(signupRequestPassengerDto);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/signup/driver")
    public ResponseEntity<SignupResponseDriverDto> signupDriver(@RequestBody SignupRequestDriverDto signupRequestDriverDto) {
        SignupResponseDriverDto response = this.authService.signupD(signupRequestDriverDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto  authRequestDto) {
        AuthResponseDto response = this.authService.login(authRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidationResponseDto> validateToken(
            @RequestBody TokenValidationRequestDto requestDto){

        /*test
        System.out.println(
                "Token received by Auth Service = "
                        + requestDto.getToken()
        );*/

        return ResponseEntity.ok(
                authService.validateToken(
                        requestDto.getToken()
                )
        );
    }
}
