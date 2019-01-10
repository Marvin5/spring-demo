package com.example.demo.core.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum PhoneType {
  LAND_LINE,
  MOBILE;

  @Converter
  public static class PhoneTypeConverter implements AttributeConverter<PhoneType, String> {

    @Override
    public String convertToDatabaseColumn(PhoneType attribute) {
      return attribute.name().substring(0, 3);
    }

    @Override
    public PhoneType convertToEntityAttribute(String dbData) {
      switch (dbData) {
        case "LAN":
          return LAND_LINE;
        case "MOB":
          return MOBILE;
        default:
          return null;
      }
    }
  }
}
