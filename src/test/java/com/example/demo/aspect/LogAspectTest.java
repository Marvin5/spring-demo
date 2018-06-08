package com.example.demo.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.EchoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogAspectTest {
  @Autowired
  private EchoService echoService;

  @Test
  public void test() {
    echoService.echo();
  }

}
