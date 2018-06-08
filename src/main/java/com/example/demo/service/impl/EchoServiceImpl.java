package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.aspect.annotation.LogAdvice;
import com.example.demo.service.EchoService;

@Service
public class EchoServiceImpl implements EchoService {
  private static final Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class);

  @Override
  @LogAdvice
  public void echo() {
    logger.info("echo");
  }

}
