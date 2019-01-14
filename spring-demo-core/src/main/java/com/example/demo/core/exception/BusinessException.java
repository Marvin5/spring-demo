package com.example.demo.core.exception;

public class BusinessException extends RuntimeException{
  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }
}
