package com.example.demo.core.service.impl;

import com.example.demo.core.entity.Person;
import com.example.demo.core.repository.PersonRepository;
import com.example.demo.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;

@Service
public class PersonServiceImpl implements PersonService {
  @Autowired private PersonRepository personRepository;

  @Override
  @Transactional("jpaTransactionManager")
  public void savePerson(@Nonnull Person p) {
    personRepository.save(p);
  }
}
