package com.example.forsparkers.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocaleValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocale {
    String message() default "Invalid locale";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
