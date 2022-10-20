package com.serratec.ecommerce.ecommerce.dto;

public class FieldMessage {

  private String fieldName;
  private String message;

  public FieldMessage(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

  public String getFieldName() {
    return this.fieldName;
  }

  public String getMessage() {
    return this.message;
  }
}