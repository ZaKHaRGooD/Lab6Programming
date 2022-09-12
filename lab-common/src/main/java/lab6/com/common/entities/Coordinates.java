package com.lab6.client.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

/**
 * Класс координат x и y
 */
// TODO: 12.09.2022 добавить implements Serializable
public class Coordinates {
    public static final double X_MAX_VALUE = 910;
    public static final double Y_MAX_VALUE = 525;

    @Max((long) X_MAX_VALUE)
    private double x; //Максимальное значение поля: 910

    @NotNull
    @Max((long) Y_MAX_VALUE)
    private Integer y; //Максимальное значение поля: 525, Поле не может быть null

    public Coordinates() {

    }

    public Coordinates(double x, Integer y) {
        if (x <= X_MAX_VALUE) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("'Координата' x может иметь максимальное значение 910.");
        }
        if (y <= Y_MAX_VALUE) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("'Координата' y может иметь максимально значение 525.");
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
