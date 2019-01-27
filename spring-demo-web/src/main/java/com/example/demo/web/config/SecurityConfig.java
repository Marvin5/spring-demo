package com.example.demo.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(
        User.builder()
            .passwordEncoder(passwordEncoder()::encode)
            .username("marvin")
            .password("123")
            .roles("USER")
            .build());
    return manager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public CorsConfigurationSource configurationSource() {
    // TODO: For the convenience of testing, need to. If you want to deploy to production, you need to modify it.
    UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
    configSource.registerCorsConfiguration("/**", corsConfiguration);
    return configSource;
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/login.jsp").permitAll()
        .anyRequest().hasRole("USER")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/login.jsp?authorization_error=true")
        .and()
        // TODO: put CSRF protection back into this endpoint
        .csrf()
        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
        .disable()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login.jsp")
        .and()
        .formLogin();
  }
}
