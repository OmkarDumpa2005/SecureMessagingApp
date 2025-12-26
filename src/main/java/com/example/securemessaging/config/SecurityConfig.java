package com.example.securemessaging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.securemessaging.security.JwtAuthenticationFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


//@EnableMethodSecurity
//@Configuration
//public class SecurityConfig {
//	@Autowired
//	JwtAuthenticationFilter jwtAuthenticationFilter;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
////        http
////            // Disable CSRF for development (required for H2 console)
////            .csrf(csrf -> csrf.disable())
////
////            // Authorization rules
////            .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/h2-console/**", "/auth/register").permitAll()
////                .anyRequest().authenticated()
////            )
////
////            // Allow H2 console frames
////            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
////
////            // TEMPORARY basic authentication
////            .httpBasic(Customizer.withDefaults());
//    	http
//        .csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/auth/register", "/auth/login", "/h2-console/**").permitAll()
//            .anyRequest().authenticated()
//        )
//        .sessionManagement(session ->
//            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        )
//        .addFilterBefore(jwtAuthenticationFilter,
//            UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//}
//


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/register",
                    "/auth/login",
                    "/h2-console/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // ✅ REQUIRED FOR H2 CONSOLE
            .headers(headers ->
                headers.frameOptions(frame -> frame.disable())
            )

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

