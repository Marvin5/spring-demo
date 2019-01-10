package com.example.demo.core.config;

import com.example.demo.core.actuator.CustomsEndPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmxConfig {

  @Bean
  public CustomsEndPoint customsEndPoint() {
    return new CustomsEndPoint();
  }
}
