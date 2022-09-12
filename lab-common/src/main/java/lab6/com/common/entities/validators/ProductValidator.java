package com.lab6.client.entities.validators;

import com.lab6.client.entities.Coordinates;
import com.lab6.client.entities.Person;
import com.lab6.client.entities.UnitOfMeasure;
import com.lab6.client.entities.Product;


/**
 * Класс для валидации значений полей объектов Product.
 */
public final class ProductValidator {
    private static final int NUMBER = 3;

    private ProductValidator() {

    }

    public static String getValidatedName(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Поле имени не может быть пустым.");
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("Ожидался один аргумент, используйте \"\" для имени в несколько слов,\n"
                    + "например: \"Синяя веревка\"");
        }
        return args[0];
    }

    public static boolean isNameValid(String name) {
        return name != null;
    }

    public static Coordinates getValidatedCoordinates(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Поля координат не могут быть пустыми");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Поля координаты должны иметь только два значения.");
        }
        Coordinates coordinates = new Coordinates(CoordinatesValidator.getValidatedX(args[0]), CoordinatesValidator.getValidatedY(args[1]));
        if (isCoordinatesValid(coordinates)) {
            return coordinates;
        } else {
            throw new IllegalArgumentException("Некорректное значение полей координат");
        }
    }

    public static boolean isCoordinatesValid(Coordinates coordinates) {
        return coordinates != null
                && CoordinatesValidator.isXValid(coordinates.getX())
                && CoordinatesValidator.isYValid(coordinates.getY());
    }

    public static long getValidatedPrice(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Поле цены не может быть пустым.");
        } else if (args.length != 1) {
            throw new IllegalArgumentException("Поле цены принимает только один аргумент.");
        }
        try {
            long price = Long.parseLong(args[0]);
            if (isPriceValid(price)) {
                return price;
            } else {
                throw new IllegalArgumentException("Некорректное значние поля цена");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неккоректный ввод поля цена.");
        }
    }

    public static boolean isPriceValid(long price) {
        return price > 0;
    }

    public static UnitOfMeasure getValidatedUnitOfMeasure(String[] args) {
        if (args.length == 0) {
            return null;
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Мера измерения должна быть одним словом.");
        }
        return UnitOfMeasureValidator.getValidatedUnitOfMeasure(args[0].toUpperCase());
    }

    public static boolean isUnitOfMeasureValid(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasure != null;
    }

    public static Person getValidatedPerson(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Поля владельца не может быть пустым.");
        }
        // if (args.length != Person.class.getDeclaredFields().length) {
        if (args.length != NUMBER) {
            throw new IllegalArgumentException("Владелец имеет три аргумента: имя, рост и id.");
        }
        return new Person(PersonValidator.getValidatedName(args[0]),
                PersonValidator.getValidatedHeight(args[1]),
                PersonValidator.getValidatedPassportID(args[2]));
    }

    public static boolean isPersonValid(Person person) {
        return person != null;
    }

    public static boolean isProductValid(Product product) {
        return isNameValid(product.getName())
                && isCoordinatesValid(product.getCoordinates())
                && isPriceValid(product.getPrice())
                && isUnitOfMeasureValid(product.getUnitOfMeasure())
                && isPersonValid(product.getOwner());
    }
}
