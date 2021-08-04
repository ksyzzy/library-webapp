package com.example.librarybackend.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { ValidEANValidator.class })
@Target( {FIELD} )
@Retention(RUNTIME)
public @interface ValidEAN {

    String message() default "EAN must contain only digits and be exactly 13 digits long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
