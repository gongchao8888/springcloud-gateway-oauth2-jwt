package com.gc.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
        		//代表project开头放行
                .pathMatchers("/project/**").permitAll()
                .anyExchange().authenticated()
                    .and()
                .oauth2Login()
                    .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .build();
    }
}

