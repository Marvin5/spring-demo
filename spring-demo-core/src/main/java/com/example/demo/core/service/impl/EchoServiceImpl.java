package com.example.demo.core.service.impl;

import com.example.demo.core.aspect.annotation.LogAdvice;
import com.example.demo.core.entity.Echo;
import com.example.demo.core.repository.EchoRepository;
import com.example.demo.core.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EchoServiceImpl implements EchoService {
  private static final Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class);
  @Autowired private EchoRepository echoRepository;

  @Override
  @LogAdvice
  @Transactional(transactionManager = "jpaTransactionManager")
  public void echo() {
    Echo entity = new Echo();
    entity.setEcho("sad");
    echoRepository.save(entity);
  }
}
