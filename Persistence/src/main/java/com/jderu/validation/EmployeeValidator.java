package com.jderu.validation;

import com.jderu.domain.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator implements CRUDValidator<Employee> {
    public EmployeeValidator() {
    }

    @Override
    public void validate(Employee entity) throws ValidationException {

    }
}