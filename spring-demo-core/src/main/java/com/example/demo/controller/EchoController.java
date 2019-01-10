package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;

@RestController
public class EchoController {
  private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

  @GetMapping("/get")
  public String get(HttpSession httpSession) {
    httpSession.setAttribute("test", "test");
    logger.debug("call");
    return "get!!!";
  }

  @PostMapping("/get")
  public String postGet() {
    logger.info("post get");
    return "post get!!!";
  }
}
