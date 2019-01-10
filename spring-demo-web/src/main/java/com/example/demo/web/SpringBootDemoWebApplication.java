package com.example.demo.web;

import com.example.demo.core.entity.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.example.demo.core", "com.example.demo.web"})
@EnableConfigurationProperties
public class SpringBootDemoWebApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootDemoWebApplication.class, args);
  }
}
