package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional("jpaTransactionManager")
    public void createCustomer(String username, String password) {
        customerRepository.save(new Customer(null, username, password));

    }

    @Override
    public boolean login(String username, String password) {
        Customer c = customerRepository.findByUsername(username);
        return c != null && c.getPassword().equals(password);
    }

    @Override
    @Transactional
    public void changePassword(String username, String password) {
        Customer c = customerRepository.findByUsername(username);
        c.setPassword(password);
        customerRepository.save(c);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        Customer c = customerRepository.findByUsername(username);
        if (c != null) {
            customerRepository.delete(c);
        }
    }
}
