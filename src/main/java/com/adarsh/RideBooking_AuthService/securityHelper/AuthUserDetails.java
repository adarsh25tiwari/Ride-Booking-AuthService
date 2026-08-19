package com.adarsh.RideBooking_AuthService.securityHelper;

import com.adarsh.RideBooking_AuthService.domain.UserRole;
import com.adarsh.RideBooking_EntityService.models.Driver;
import com.adarsh.RideBooking_EntityService.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {
    //email is username
    private String username;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUserDetails(Passenger passenger) {
        this.username=passenger.getEmail();
        this.password=passenger.getPassword();
        this.authorities =List.of(new SimpleGrantedAuthority(UserRole.ROLE_PASSENGER.name()));
    }

    public AuthUserDetails(Driver driver) {
        this.username=driver.getEmail();
        this.password=driver.getPassword();
        this.authorities =List.of(new SimpleGrantedAuthority(UserRole.ROLE_DRIVER.name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    // No Need of below methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true   ;
    }
}
