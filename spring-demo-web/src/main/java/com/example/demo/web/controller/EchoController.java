package com.example.demo.web.controller;

import com.example.demo.core.exception.BusinessException;
import com.example.demo.web.exception.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/pub/echo")
public class EchoController {
  private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

  @GetMapping
  public String get() {
    throw new BusinessException("business error");
  }

  @GetMapping("/get")
  public String postGet() {
    logger.info("post get");
    return "post get!!!";
  }
}
