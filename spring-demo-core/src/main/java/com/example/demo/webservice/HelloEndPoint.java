package com.example.demo.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "HelloEndPoint")
@Component
public class HelloEndPoint {
  @WebMethod
  public String hello() {
    return "hello";
  }
}
