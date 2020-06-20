package com.exam.validation;

import com.exam.domain.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskValidator implements CRUDValidator<Task> {
    public TaskValidator() {
    }

    @Override
    public void validate(Task entity) throws ValidationException {

    }
}