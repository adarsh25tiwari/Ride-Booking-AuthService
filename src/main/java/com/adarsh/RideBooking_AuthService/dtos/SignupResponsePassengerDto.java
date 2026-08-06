package com.adarsh.RideBooking_AuthService.dtos;

import com.adarsh.RideBooking_EntityService.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponsePassengerDto {

    private Long passengerId;
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
    private Date createdAt;

    public static SignupResponsePassengerDto fromPassenger(Passenger passenger) {

        return SignupResponsePassengerDto.builder()
                .passengerId(passenger.getId())
                .passengerName(passenger.getName())
                .passengerEmail(passenger.getEmail())
                .passengerPhone(passenger.getPhoneNumber())
                .createdAt(passenger.getCreatedAt())
                .build();
    }
}
