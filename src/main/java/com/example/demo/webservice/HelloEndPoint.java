package com.example.demo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

@WebService(serviceName = "HelloEndPoint")
@Component
public class HelloEndPoint {
	@WebMethod
	public String hello() {
		return "hello";
	}
	
}
