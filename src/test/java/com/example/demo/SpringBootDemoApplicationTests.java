package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.Phone;
import com.example.demo.entity.PhoneType;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PersonService;
import com.example.demo.service.PhoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
  private static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);

  @Autowired
  private CustomerService customerService;
  @Autowired
  private PhoneService phoneService;
  @Autowired
  private PhoneRepository phoneRepository;

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private PersonService personService;

  @Test
  public void test() {
    Optional<Phone> phoneOptioanl = phoneRepository.findById("19fc9bf8-4bd0-4876-9852-ca6ccbb88c03");
    Phone phone = phoneOptioanl.get();
    phone.setNumber("123456");
    phoneService.createPhone(phone);
  }

}
