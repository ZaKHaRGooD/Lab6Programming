package com.lab6.client.entities.validators;

import com.lab6.client.entities.UnitOfMeasure;

/**
 * Класс для валидации значений полей объектов UnitOfMeasure.
 */
public final class UnitOfMeasureValidator {
    private UnitOfMeasureValidator() {

    }

    public static UnitOfMeasure getValidatedUnitOfMeasure(String unitOfMeasureArg) {
        try {
            return UnitOfMeasure.valueOf(unitOfMeasureArg);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректная имя меры измеренения, посмотрите на список внимательно и выберите из предложенных.");
        }
    }

}
