package com.adarsh.RideBooking_AuthService.dtos;

import com.adarsh.RideBooking_EntityService.models.Driver;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDriverDto {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String licenceNumber;
    private String aadharNumber;

}
