package com.jalasoft.bootcamp.gateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class ZuulConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // Activate cors configuration
        http.cors().and();
        // Generate the entry point so Zuul can redirect to the login in case the user is not authenticated.
        http
                // Disable csrf.
                .csrf().disable()
                // make sure we use stateless session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // Authorize all calls through the gateway
                .antMatchers("/**").permitAll();
    }
}
