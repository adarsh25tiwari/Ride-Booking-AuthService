package com.adarsh.RideBooking_AuthService.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenValidationResponseDto {
    private Boolean valid;
    private String email;
    private String role;
}
