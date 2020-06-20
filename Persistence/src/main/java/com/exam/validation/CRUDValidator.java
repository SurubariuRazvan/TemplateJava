package com.exam.validation;

import org.springframework.stereotype.Component;

public interface CRUDValidator<E> {
    /**
     * @param entity the entity to be validated
     * @throws ValidationException if the entity doesnt meet the specified characteristics
     */
    void validate(E entity) throws ValidationException;
}
