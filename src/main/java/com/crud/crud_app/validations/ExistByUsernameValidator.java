package com.crud.crud_app.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.crud_app.services.user.IUserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidator implements ConstraintValidator<ExistByUsername, String> {

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.existByUsername(value);
    }

}
