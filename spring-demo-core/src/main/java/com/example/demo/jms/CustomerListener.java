package com.example.demo.jms;

import com.example.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class CustomerListener {
  private static final Logger logger = LoggerFactory.getLogger(CustomerListener.class);
  @Autowired private CustomerService customerSerice;

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
