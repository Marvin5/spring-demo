package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @OneToMany(cascade = { CascadeType.ALL })
  private List<Phone> phones = new ArrayList<>();
  @Version
  private int version;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
