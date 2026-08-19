package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.repository.DriverRepository;
import com.adarsh.RideBooking_AuthService.repository.PassengerRepository;
import com.adarsh.RideBooking_AuthService.securityHelper.AuthUserDetails;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Passenger> passenger = passengerRepository.findByEmail(username);
      Optional<Driver> driver = driverRepository.findByEmail(username);

      if (passenger.isPresent()) {
          //convert passenger obj to userDetail obj for authentication
          return new AuthUserDetails(passenger.get());
      }
      if(driver.isPresent()) {
          //convert driver obj to userDetail obj for authentication
          return new AuthUserDetails(driver.get());
      }
      throw new UsernameNotFoundException(username);

    }
}
