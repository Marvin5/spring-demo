package com.example.demo.util;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

  @Test
  public void test() {
    A a = new A();
    Optional<A> optionalA = Optional.of(a);
    String aa = optionalA.map((t) -> t.a).get();

  }

  public static class A {
    public String a;
    public String b;
  }

}

