package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "phone")
@GenericGenerator(name = "phone_id_generator", strategy = "org.hibernate.id.UUIDGenerator")
public class Phone {

    @Id
    @GeneratedValue(generator = "phone_id_generator")
    private String id;
    private String number;
    // @Enumerated(EnumType.STRING)
    // testing for jpa converter
    @Convert(converter = PhoneType.PhoneTypeConverter.class)
    private PhoneType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
