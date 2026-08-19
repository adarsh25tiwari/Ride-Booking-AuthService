package com.adarsh.RideBooking_AuthService.dtos;

import lombok.*;

@Getter
@Setter
public class AuthResponseDto {
    private String token;
    private String role;

    public AuthResponseDto(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
