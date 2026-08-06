package com.adarsh.RideBooking_AuthService.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestPassengerDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

}
