package com.broad.web.framework.annotation.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.broad.web.framework.tool.validator.NumericValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
@Documented
public @interface Numeric {

	String message() default "不合法的数字";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}