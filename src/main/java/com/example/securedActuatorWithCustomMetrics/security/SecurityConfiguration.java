package com.example.securedActuatorWithCustomMetrics.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
public class SecurityConfiguration {

    private final static int ENCODER_STRENGTH = 4;
    private final static String BEER_METRICS_READER_ROLE = "BEER_METRICS_READER";

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(ENCODER_STRENGTH);
    }

    @Bean
    public InMemoryUserDetailsManager InMemoryUserDetailsManager(PasswordEncoder encoder) {
        final var password = encoder.encode("a");
        final var user = User.withUsername("operator")
                .password(password)
                .roles(BEER_METRICS_READER_ROLE)
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/beer/*").permitAll()
                .requestMatchers("/actuator/metrics/beer.orders").hasRole(BEER_METRICS_READER_ROLE);
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable(); // should access only via REST API
        return httpSecurity.build();
    }

}
