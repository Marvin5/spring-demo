package com.example.demo.core.service;

import com.example.demo.core.entity.Person;

import javax.annotation.Nonnull;

public interface PersonService {
  void savePerson(@Nonnull Person p);
}
