package com.example.demo.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@GenericGenerator(name = "customer_id_generator", strategy = "org.hibernate.id.UUIDGenerator")
public class Customer {
  @Id
  @GeneratedValue(generator = "customer_id_generator")
  private String id;

  private String username;
  private String password;

  public Customer() {}

  public Customer(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", username=" + username + ", password=" + password + "]";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
