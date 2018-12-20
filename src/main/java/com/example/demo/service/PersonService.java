package com.example.demo.service;

import com.example.demo.entity.Person;

import javax.annotation.Nonnull;

public interface PersonService {
  void savePerson(@Nonnull Person p);
}
