package com.example.demo;

import com.example.demo.calculator.Calculator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJTest {
  private Calculator calculator;

  @Before
  public void setUp() {
    calculator = new Calculator();
  }

  @Test
  public void test1() {
    assertThat(calculator.add(1, 1)).isEqualTo(2);
  }
}
