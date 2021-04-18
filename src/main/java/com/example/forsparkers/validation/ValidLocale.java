package com.example.forsparkers.validation;

import com.example.forsparkers.util.ErrorMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocaleValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocale {
    String message() default ErrorMessage.INVALID_LOCALE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
