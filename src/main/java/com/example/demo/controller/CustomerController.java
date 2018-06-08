package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public boolean login(String username, String password) {
		return customerService.login(username, password);
	}
	@PostMapping
	public void register(String username,String password) {
		customerService.createCustomer(username, password);
	}
	@PutMapping 
	public void changePassword(String username,String password) {
		customerService.changePassword(username, password);
	}
	@DeleteMapping
	public void deleteUser(String username) {
		customerService.deleteUser(username);
	}
}
