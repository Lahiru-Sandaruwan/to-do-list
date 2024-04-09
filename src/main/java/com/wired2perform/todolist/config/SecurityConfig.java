/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 11:14 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.config;

import com.wired2perform.todolist.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetail userDetail;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/register" ,"/h2-console/**").permitAll()
                .anyRequest().authenticated())
                .userDetailsService(userDetail)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}

