package com.lab6.client.entities;

import jakarta.validation.constraints.NotNull;

/**
 * Класс владелец продукта
 */
public class Person {
    private String passportID; //Длина строки не должна быть больше 30, Значение этого поля должно быть уникальным, Поле может быть null

    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Double height; //Поле может быть null, Значение поля должно быть больше 0

    public Person() {

    }

    public Person(String name, Double height, String passportID) {
        this.name = name;
        this.height = height;
        this.passportID = passportID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Рост владельца должен быть больше нуля.");
        }
        this.height = height;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "[id: " + passportID + ", name: " + name + ", height: " + height + "]";
    }
}
