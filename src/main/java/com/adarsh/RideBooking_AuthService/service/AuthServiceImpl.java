package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.dtos.*;
import com.adarsh.RideBooking_AuthService.repository.DriverRepository;
import com.adarsh.RideBooking_AuthService.repository.PassengerRepository;
import com.adarsh.RideBooking_AuthService.securityHelper.AuthUserDetails;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService  jwtService;

    public AuthServiceImpl(PassengerRepository passengerRepository,
                           DriverRepository driverRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {

        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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


    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequestDto.getEmail(),
                                authRequestDto.getPassword()
                        )
                );
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        String jwtToken = jwtService.createJwtToken(userDetails);
        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        role = role.replace("ROLE_", "");

        return new AuthResponseDto(jwtToken, role);


    }

    @Override
    public TokenValidationResponseDto validateToken(String token) {

        boolean valid = jwtService.validateToken(token);

        if(!valid){
            return TokenValidationResponseDto.builder()
                    .valid(false)
                    .build();
        }
        String email = jwtService.extractEmail(token);

        return TokenValidationResponseDto.builder()
                .valid(true)
                .email(email)
                .role(
                        ((List<?>) jwtService.extractAllPayloads(token).get("roles"))
                                .get(0)
                                .toString())
                .build();
    }
}
