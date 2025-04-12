package com.example.finalproject.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(
                                "/", "/home", "/about", "/healthy-food", "/kids-zone",
                                "/tennis", "/swimming", "/fitness", "/gymnastics"
                        ).permitAll()
                        .requestMatchers("/pic/**").permitAll()
                        .requestMatchers("/users/login", "/users/register", "/users/logout", "/users/profile","/users/edit-profile").permitAll()
                        .requestMatchers("/users/plans", "/users/savePlan").authenticated()
                        .requestMatchers("/users/remove-plan/**").hasRole("ADMIN")
                        .requestMatchers("/users/admin-dashboard").hasRole("ADMIN")
                        .requestMatchers("/users/delete-user").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()

                )
                .formLogin(login -> login
                        .loginPage("/users/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/users/login")
                        .permitAll()
                ).rememberMe(remember -> remember
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                        .userDetailsService(userDetailsService)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}
