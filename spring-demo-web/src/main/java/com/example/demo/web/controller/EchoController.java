package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
  private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

  @GetMapping("/pub/echo")
  public String pubGet() {
    return "pub get";
  }

  @PostMapping("/pub/echo")
  public String pubPost() {
    return "pub post";
  }

  @GetMapping("/echo")
  public String get() {
    return "get";
  }

  @PostMapping("/echo")
  public String post() {
    return "post";
  }
}
