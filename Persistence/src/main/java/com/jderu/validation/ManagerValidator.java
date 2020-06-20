package com.jderu.validation;

import com.jderu.domain.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerValidator implements CRUDValidator<Manager> {
    public ManagerValidator() {
    }

    @Override
    public void validate(Manager entity) throws ValidationException {

    }
}