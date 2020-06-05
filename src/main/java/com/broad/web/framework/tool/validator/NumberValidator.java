package com.broad.web.framework.tool.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.broad.web.framework.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;

import com.broad.web.framework.annotation.validator.Number;

public class NumberValidator implements ConstraintValidator<Number,String> {


	public boolean isValid(String number, ConstraintValidatorContext constraintContext) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		
		return PatternUtil.isNumber(number);
	}

	public void initialize(Number constraintAnnotation) {}

}