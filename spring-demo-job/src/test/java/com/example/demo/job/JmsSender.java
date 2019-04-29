package com.example.demo.job;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.print.DocFlavor;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JmsSender {
  @Autowired private JmsTemplate jmsTemplate;

  @Test
  public void sendMessage() {
    jmsTemplate.send("test.que", new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setStringProperty("env", "marvin-test");
        message.setText("asd");
        return message;

      }
    });
  }
}
