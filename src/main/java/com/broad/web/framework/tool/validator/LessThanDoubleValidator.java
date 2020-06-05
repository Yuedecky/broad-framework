package com.broad.web.framework.tool.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.broad.web.framework.annotation.validator.LessThanDouble;

public class LessThanDoubleValidator implements ConstraintValidator<LessThanDouble,Number> {

	private double lessThanValue;
	
	public boolean isValid(Number value, ConstraintValidatorContext constraintContext) {
		if ( value == null ) {
			return true;
		}
		return new BigDecimal(value.doubleValue()).setScale(2).doubleValue() <= new BigDecimal(lessThanValue).setScale(2).doubleValue();
	}

	public void initialize(LessThanDouble constraintAnnotation) {
		this.lessThanValue = constraintAnnotation.lessThanValue();
		
	}
}