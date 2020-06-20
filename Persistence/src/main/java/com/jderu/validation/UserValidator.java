package com.jderu.validation;

import com.jderu.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements CRUDValidator<User> {
    public UserValidator() {
    }

    @Override
    public void validate(User entity) throws ValidationException {

    }
}