package com.adarsh.RideBooking_AuthService.service;

import com.adarsh.RideBooking_AuthService.repository.PassengerRepository;
import com.adarsh.RideBooking_AuthService.securityHelper.AuthPassengerDetails;
import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Passenger> passenger = passengerRepository.findByEmail(username);

      if (passenger.isPresent()) {
          //convert passenger obj to userDetail obj for authentication
          return new AuthPassengerDetails(passenger.get());
      }else{
          throw  new UsernameNotFoundException(username+" "+"Passenger not found");
      }
    }
}
