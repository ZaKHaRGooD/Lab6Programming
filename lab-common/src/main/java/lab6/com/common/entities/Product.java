package com.lab6.client.entities;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Класс продукта
 */
public class Product implements Comparable<Product> {
    private static long previousId = 0L;

    @NotNull
    private final ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long id = previousId++ + 1; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    private long price; //Поле не может быть null, Значение поля должно быть больше 0

    @NotNull
    private UnitOfMeasure unitOfMeasure; //Поле может быть null

    @NotNull
    private Person owner; //Поле может быть null

    public Product() {

    }

    public Product(String name, Coordinates coordinates, long price, UnitOfMeasure unitOfMeasure, Person owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId() {
        id = previousId++ + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым.");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут не иметь значения.");
        }
        this.coordinates = coordinates;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше нуля.");
        }
        this.price = price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            throw new IllegalArgumentException("Мера измерения не может быть пустой.");
        }
        this.unitOfMeasure = unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Продукт не может существовать без владельца.");
        }
        this.owner = owner;
    }

    // дописать сравнение
    @Override
    public int compareTo(Product anotherProduct) {
        if (Comparator.comparing(Product::getName).thenComparingLong(Product::getPrice).compare(this, anotherProduct) < 0) {
            System.out.println("Элемент: " + this + "\nбыл удачно удален");
        }
        return Comparator.comparing(Product::getName).thenComparingLong(Product::getPrice).compare(this, anotherProduct);
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", coordinates: " + coordinates + ", creationDate: " + creationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm")) + ", price: " + price + ", unit of measure: " + unitOfMeasure + ", owner: " + owner;

    }
}
