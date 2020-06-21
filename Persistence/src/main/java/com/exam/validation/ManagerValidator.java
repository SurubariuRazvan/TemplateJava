package com.exam.validation;

import com.exam.domain.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerValidator implements CRUDValidator<Manager> {
    public ManagerValidator() {
    }

    @Override
    public void validate(Manager entity) throws ValidationException {

    }
}