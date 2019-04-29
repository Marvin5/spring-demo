package com.example.demo.job.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class CustomerListener {
  private static final Logger logger = LoggerFactory.getLogger(CustomerListener.class);

  @JmsListener(destination = "test.que")
  @Transactional(transactionManager = "jmsTransactionManager")
  public void listen(Message m) {

    TextMessage tm = (TextMessage) m;
    try {
      logger.debug("hotswap test");
      // logger.debug(((TextMessage) m).getText());
    } catch (Exception e) {
      logger.error("error", e);
    }
  }
}
