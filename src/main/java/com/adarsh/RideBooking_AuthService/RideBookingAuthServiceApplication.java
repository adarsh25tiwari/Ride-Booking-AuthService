package com.adarsh.RideBooking_AuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan("com.adarsh.RideBooking_EntityService.models")
@EnableJpaAuditing
@EnableDiscoveryClient
public class RideBookingAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideBookingAuthServiceApplication.class, args);
	}

}
