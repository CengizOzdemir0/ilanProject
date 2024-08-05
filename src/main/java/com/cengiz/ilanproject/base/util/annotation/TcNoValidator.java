package com.cengiz.ilanproject.base.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.cengiz.ilanproject.base.util.ValidationUtil;


public class TcNoValidator implements ConstraintValidator<TcNo, Long> {

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    return ValidationUtil.tcNoValidation(value);
  }


}
