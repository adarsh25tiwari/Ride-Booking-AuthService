package com.adarsh.RideBooking_AuthService.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDto {
    private String email;
    private String password;
}
