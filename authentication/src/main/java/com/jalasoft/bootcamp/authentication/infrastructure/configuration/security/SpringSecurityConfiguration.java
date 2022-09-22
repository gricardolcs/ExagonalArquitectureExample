package com.jalasoft.bootcamp.authentication.infrastructure.configuration.security;

import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch.UserFetchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter
{

    private final UserFetchUseCase userDetailsService;
    private final CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
    private final BootcampAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SpringSecurityConfiguration(
        final UserFetchUseCase userDetailsService,
        final CustomJwtAuthenticationFilter customJwtAuthenticationFilter,
        final BootcampAuthenticationEntryPoint jwtAuthenticationEntryPoint)
    {
        this.userDetailsService = userDetailsService;
        this.customJwtAuthenticationFilter = customJwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers(HttpMethod.GET, "/logout").permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
            .logoutSuccessUrl("/logout")
            .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(
            customJwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers(
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**");
    }
}
