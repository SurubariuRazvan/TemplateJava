package com.exam.validation;

import com.exam.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements CRUDValidator<User> {
    public UserValidator() {
    }

    @Override
    public void validate(User entity) throws ValidationException {

    }
}