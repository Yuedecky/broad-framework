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

import com.broad.web.framework.tool.validator.LessThanDoubleValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LessThanDoubleValidator.class)
@Documented
public @interface LessThanDouble {
	
	String message() default "小于某个数字校验失败";
	
	double lessThanValue();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}