package com.serratec.ecommerce.ecommerce.service.exceptions;

public class DatabaseExcption extends RuntimeException {

  public DatabaseExcption(String message) {
    super(message);
  }
}