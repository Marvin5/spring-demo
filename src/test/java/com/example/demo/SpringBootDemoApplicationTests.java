package com.example.demo;

import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PhoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test() {
        logger.info("phone is {}", phoneRepository.findAll());
        byte[] a;

    }

}
