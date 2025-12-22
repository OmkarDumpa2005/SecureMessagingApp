package com.example.securemessaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for development (required for H2 console)
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/auth/register").permitAll()
                .anyRequest().authenticated()
            )

            // Allow H2 console frames
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            // TEMPORARY basic authentication
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
