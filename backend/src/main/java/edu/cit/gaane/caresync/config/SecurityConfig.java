package edu.cit.gaane.caresync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtFilter;


    public SecurityConfig(
            JwtAuthenticationFilter jwtFilter
    ){
        this.jwtFilter = jwtFilter;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }



    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/api/auth/**"
                )
                .permitAll()


                .requestMatchers(
                        "/api/dashboard/**"
                )
                .hasAuthority("Admin")


                .requestMatchers(
                        "/api/users/**"
                )
                .hasAuthority("Admin")

                .requestMatchers(
                        "/api/patients/**"
                )
                .hasAnyAuthority(
                        "Clinic Staff",
                        "Doctor"
                )

                .requestMatchers(
                        "/api/consultations/**"
                )
                .hasAuthority(
                        "Doctor"
                )
                


                    .anyRequest()
                    .authenticated()

            )


            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
            );


        return http.build();

    }

}