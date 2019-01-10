package com.example.demo.core.service.impl;

import com.example.demo.core.aspect.annotation.LogAdvice;
import com.example.demo.core.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {
  private static final Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class);

  @Override
  @LogAdvice
  public void echo() {
    logger.info("echo");
  }
}
