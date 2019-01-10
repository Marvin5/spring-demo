package com.example.demo.core.repository;

import com.example.demo.core.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {}
