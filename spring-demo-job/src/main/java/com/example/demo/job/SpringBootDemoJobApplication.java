package com.example.demo.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.example.demo.core", "com.example.demo.job"})
@EnableConfigurationProperties
public class SpringBootDemoJobApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootDemoJobApplication.class, args);
  }
}
