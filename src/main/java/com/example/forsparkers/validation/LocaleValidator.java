package com.example.forsparkers.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.MissingResourceException;

public class LocaleValidator implements ConstraintValidator<ValidLocale, String> {

    @Override
    public void initialize(ValidLocale constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Locale locale = StringUtils.parseLocaleString(value);
        if (locale == null) {
            return false;
        }
        try {
            return locale.getISO3Language() != null && locale.getISO3Country() != null;
        } catch (MissingResourceException e) {
            return false;
        }
    }
}
