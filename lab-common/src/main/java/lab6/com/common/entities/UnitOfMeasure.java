package com.lab6.client.entities;

/**
 * Перечисляемый класс для меры измерения
 */
public enum UnitOfMeasure {
    KILOGRAMS("Kilograms"),
    METERS("Meters"),
    CENTIMETERS("Centimeters"),
    MILLILITERS("Milliliters"),
    MILLIGRAMS("Milligrams");

    private final String name;

    UnitOfMeasure(String name) {
        this.name = name;
    }

    public static String getAvailableUnitOfMeasure() {
        StringBuilder unitOfMeasures = new StringBuilder();
        for (UnitOfMeasure unitOfMeasure : UnitOfMeasure.values()) {
            unitOfMeasures.append(unitOfMeasure.name());
            unitOfMeasures.append(" ");
        }
        return unitOfMeasures.toString();
    }

    public String getMeasure() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
