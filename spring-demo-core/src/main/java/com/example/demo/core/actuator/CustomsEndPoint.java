package com.example.demo.core.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "hahaha")
public class CustomsEndPoint {
  private Integer anInt = 1;

  @ReadOperation
  public int getAnInt() {
    return anInt;
  }

  @WriteOperation
  public void setAnInt(Integer anInt) {
    this.anInt = anInt;
  }

  @DeleteOperation
  public void deleteAnInt() {
    this.anInt = null;
  }
}
