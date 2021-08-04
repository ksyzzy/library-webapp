package com.example.librarybackend.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEANValidator implements ConstraintValidator<ValidEAN, String> {

    private ValidEAN validEAN;

    @Override
    public void initialize(ValidEAN constraintAnnotation) {
        this.validEAN = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.chars().allMatch(Character::isDigit) && value.length() == 13;
    }
}
