package com.lab6.client.entities.validators;

import com.lab6.client.entities.Coordinates;

// перепроверить валидацию координаты y

/**
 * Класс для валидации значений полей объектов Coordinates.
 */
public final class CoordinatesValidator {
    private CoordinatesValidator() {

    }

    public static double getValidatedX(String xArg) {
        try {
            double x = Double.parseDouble(xArg);
            if (Double.isInfinite(x)) {
                throw new IllegalArgumentException("Некорректное значение аргумента для поля x, x не может быть бесконечностью");
            }
            return x;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректное значение аргумента x, данная строка не похожа на число.");
        }
    }

    public static boolean isXValid(double x) {
        return x <= Coordinates.X_MAX_VALUE;
    }

    public static int getValidatedY(String yArg) {
        try {
            Integer y = Integer.parseInt(yArg);
            if (y == null) {
                throw new IllegalArgumentException("Некорректное значние аргумента для поля y, y не может быть пустым");
            }
            return y;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректное значение аргумента y, данная строка не похожа на число.");
        }
    }

    public static boolean isYValid(int y) {
        return y <= Coordinates.Y_MAX_VALUE;
    }
}
