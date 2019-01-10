package com.example.demo.core.repository;

import com.example.demo.core.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
  Customer findByUsername(String username);
}
