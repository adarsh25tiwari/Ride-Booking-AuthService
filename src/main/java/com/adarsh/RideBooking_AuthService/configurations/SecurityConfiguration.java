package com.adarsh.RideBooking_AuthService.configurations;

import com.adarsh.RideBooking_AuthService.repository.PassengerRepository;
import com.adarsh.RideBooking_AuthService.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration  {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeRequests(auth ->
                           auth
                               .requestMatchers("/api/v1/auth/signup/*").permitAll()
                               .requestMatchers("/api/v1/auth/login/*").permitAll())
            .csrf(AbstractHttpConfigurer::disable)
            .build();
    }

    //AuthenticationProvider provides a logic that how to authenticate user
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    //AuthenticationManager calls AuthenticationProvider
    @Bean
    public AuthenticationManager  authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
    @Bean
    public BCryptPasswordEncoder BcryptpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
