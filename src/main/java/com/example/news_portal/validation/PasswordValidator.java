package com.example.news_portal.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValid,String> {

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^[a-zA-Z0-9]{5,14}$";
        Pattern p = Pattern.compile(regex);

        if(password == null) {
            return false;
        }

        Matcher m = p.matcher(password);
        return m.matches();
    }
}