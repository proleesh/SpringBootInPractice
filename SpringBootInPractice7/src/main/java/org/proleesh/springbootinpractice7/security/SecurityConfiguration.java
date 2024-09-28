package org.proleesh.springbootinpractice7.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                a -> a.requestMatchers("/adduser", "/login", "login-error").permitAll()
                        .requestMatchers("/webjars/**", "/images/*", "/css/*").permitAll()
                        .anyRequest().authenticated());
        http.formLogin(a -> a.loginPage("/login").failureUrl("/login-error"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
