package com.lab6.client.entities.validators;

import java.util.ArrayList;


/**
 * Класс для валидации значений полей объектов Person
 */
public final class PersonValidator {
    private static final ArrayList<String> PASSPORTIDLIST = new ArrayList<>();
    private static final int NUMBER = 30;

    private PersonValidator() {

    }

    public static String getValidatedName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя владельца не может быть пустым.");
        }
        return name;
    }

    public static Double getValidatedHeight(String heightArg) {
        try {
            double height = Double.parseDouble(heightArg);
            if (Double.isInfinite(height)) {
                throw new IllegalArgumentException("Некорректное значение аргумента для поля x, x не может быть бесконечностью\"");
            }
            if (height <= 0) {
                throw new IllegalArgumentException("Рост владельца должен быть больше нуля");
            }
            return height;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректное значение аргумента height, данная строка не похожа на число.");
        }
    }

    public static String getValidatedPassportID(String passportID) {
        if (passportID.length() > NUMBER) {
            throw new IllegalArgumentException("PassportID нем может быть больше 30 символов.");
        }
        if (passportID == null) {
            throw new IllegalArgumentException("PassportID не может быть пустым.");
        }
        if (PASSPORTIDLIST.contains(passportID)) {
            throw new IllegalArgumentException("PassportID должене быть уникальным.");
        }
        PASSPORTIDLIST.add(passportID);
        return passportID;
    }
}
