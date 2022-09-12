package com.lab6.client.entities;

import com.lab6.client.entities.validators.ProductValidator;
import com.lab6.client.utils.DataNormalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Загрузчик объекта типа продукт
 */
public final class ProductLoader {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private ProductLoader() {

    }

    public static String loadName(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print(ANSI_GREEN + "Введите имя продукта" + ANSI_RESET
                    + "\nнапример: \"бечёвка\", имя продукта не может быть пустым");
            if (!"".equals(currentValue)) {
                System.out.print(", текущие значение - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return ProductValidator.getValidatedName(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static String loadName(Reader reader) throws IOException {
        return loadName(reader, "");
    }

    public static long loadPrice(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print(ANSI_GREEN + "Введите стоимость продукта" + ANSI_RESET + "\nнапример: \"1299\", введите число");
            if (!"".equals(currentValue)) {
                System.out.print(", текущие значение - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return ProductValidator.getValidatedPrice(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static long loadPrice(Reader reader) throws IOException {
        return loadPrice(reader, "");
    }


    public static Coordinates loadCoordinates(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print(ANSI_GREEN + "Введите координаты" + ANSI_RESET + "\nвведите x и y через пробел, например: \"120 12\","
                    + "\nx может быть не больше 910, y может быть не больше 525 и не может быть пустым");
            if (!"".equals(currentValue)) {
                System.out.print(", текущие значение - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return ProductValidator.getValidatedCoordinates(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Coordinates loadCoordinates(Reader reader) throws IOException {
        return loadCoordinates(reader, "");
    }

    public static UnitOfMeasure loadUnitOfMeasure(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print(ANSI_GREEN + "Введите меру измерения" + ANSI_RESET + "\nвыберете меру измерения из списка ниже и введите его,"
                            + "\nполе не может быть пустым");
            if (!"".equals(currentValue)) {
                System.out.print(", текущие значение - " + currentValue);
            }
            System.out.println();
            System.out.println(UnitOfMeasure.getAvailableUnitOfMeasure());
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return ProductValidator.getValidatedUnitOfMeasure(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static UnitOfMeasure loadUnitOfMeasure(Reader reader) throws IOException {
        return loadUnitOfMeasure(reader, "");
    }

    public static Person loadPerson(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print(ANSI_GREEN + "Введите имя, рост и id пользователя через пробел" + ANSI_RESET
                    + "\nнапример \"Миша 180 23\",\nимя не может быть пустым");
            if (!"".equals(currentValue)) {
                System.out.print(", текущие значение - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return ProductValidator.getValidatedPerson(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static Person loadPerson(Reader reader) throws IOException {
        return loadPerson(reader, "");
    }

    public static Product loadProduct(Reader reader) throws IOException {
        return new Product(
                loadName(reader),
                loadCoordinates(reader),
                loadPrice(reader),
                loadUnitOfMeasure(reader),
                loadPerson(reader)
        );
    }
}
