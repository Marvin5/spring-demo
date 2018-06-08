package com.example.demo.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testSendJms() throws JsonProcessingException {
		Customer c = new Customer(null, "marvin", "123456");
		ObjectMapper mapper = new ObjectMapper();
		String message = mapper.writeValueAsString(c);
		jmsTemplate.send("test.que", session -> session.createTextMessage(message));
	}

}
