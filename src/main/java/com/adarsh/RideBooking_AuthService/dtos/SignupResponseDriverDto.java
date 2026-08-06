package com.adarsh.RideBooking_AuthService.dtos;

import com.adarsh.RideBooking_EntityService.models.Driver;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponseDriverDto {

    private Long  driverId;
    private String driverName;
    private String driverEmail;
    private String aadharNumber;
    private String licenceNumber;
    private Date createdAt;

    public static SignupResponseDriverDto fromDriver(Driver driver) {

        return SignupResponseDriverDto.builder()
                .driverId(driver.getId())
                .driverName(driver.getName())
                .driverEmail(driver.getEmail())
                .aadharNumber(driver.getAadharCard())
                .licenceNumber(driver.getLicenceNumber())
                .createdAt(driver.getCreatedAt())
                .build();
    }
}
