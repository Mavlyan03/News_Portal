package com.example.news_portal.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RUNTIME)
public @interface PasswordValid {

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
