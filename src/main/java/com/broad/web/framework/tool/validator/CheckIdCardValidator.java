package com.broad.web.framework.tool.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.broad.web.framework.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;

import com.broad.web.framework.annotation.validator.IdCard;

public class CheckIdCardValidator implements ConstraintValidator<IdCard,String> {


	public boolean isValid(String idCard, ConstraintValidatorContext constraintContext) {
		if (StringUtils.isBlank(idCard)) {
			return false;
		}
		
		return PatternUtil.checkIdentity(idCard);
	}

	public void initialize(IdCard constraintAnnotation) {}

}