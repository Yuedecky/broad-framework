package com.broad.web.framework.tool.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.broad.web.framework.annotation.validator.GreaterThanDouble;

public class GreaterThanDoubleValidator implements ConstraintValidator<GreaterThanDouble,Number> {

	private double greaterThanValue;
	
	public boolean isValid(Number value, ConstraintValidatorContext constraintContext) {
		if ( value == null ) {
			return true;
		}
		return new BigDecimal(value.doubleValue()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue() >= new BigDecimal(greaterThanValue).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
	}

	public void initialize(GreaterThanDouble constraintAnnotation) {
		this.greaterThanValue = constraintAnnotation.greaterThanValue();
	}
}