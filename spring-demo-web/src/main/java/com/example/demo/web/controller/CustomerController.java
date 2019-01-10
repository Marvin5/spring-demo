package com.example.demo.web.controller;

import com.example.demo.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
  @Autowired private CustomerService customerService;

  @GetMapping
  public boolean login(String username, String password) {
    return customerService.login(username, password);
  }

  @PostMapping
  public void register(String username, String password) {
    customerService.createCustomer(username, password);
  }

  @PutMapping
  public void changePassword(String username, String password) {
    customerService.changePassword(username, password);
  }

  @DeleteMapping
  public void deleteUser(String username) {
    customerService.deleteUser(username);
  }
}
