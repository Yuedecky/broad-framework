package com.broad.web.framework.tool.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.broad.web.framework.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;

import com.broad.web.framework.annotation.validator.Numeric;

public class NumericValidator implements ConstraintValidator<Numeric,String> {


	public boolean isValid(String number, ConstraintValidatorContext constraintContext) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		
		return PatternUtil.isNumeric(number);
	}

	public void initialize(Numeric constraintAnnotation) {}

}