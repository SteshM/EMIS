package com.emis.EMIS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class Configurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    return http
            .csrf((csrf)->csrf.disable())
            .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers("/v1/user/all/**").permitAll())
            .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.anyRequest().authenticated())
            .sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new CustomAuthorization(), UsernamePasswordAuthenticationFilter.class)
            .build();
}

}
