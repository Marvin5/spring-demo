package com.example.demo.jms;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerListener {
  private static final Logger logger = LoggerFactory.getLogger(CustomerListener.class);
  @Autowired
  private CustomerService customerSerice;

  @JmsListener(destination = "test.que")
  @Transactional(transactionManager = "jmsTransactionManager")
  public void listen(Message m) {
    TextMessage tm = (TextMessage) m;
    ObjectMapper mapper = new ObjectMapper();
    try {
      Customer c = mapper.readValue(tm.getText(), Customer.class);
      customerSerice.createCustomer(c.getUsername(), c.getPassword());
      logger.debug("persist {} success", c);
    } catch (IOException | JMSException e) {
      logger.error("error", e);
    }
  }
}
