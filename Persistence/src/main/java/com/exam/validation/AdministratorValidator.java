package com.exam.validation;

import com.exam.domain.Administrator;
import org.springframework.stereotype.Component;

@Component
public class AdministratorValidator implements CRUDValidator<Administrator> {
    public AdministratorValidator() {
    }

    @Override
    public void validate(Administrator entity) throws ValidationException {

    }
}