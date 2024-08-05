package com.cengiz.ilanproject.base.util.annotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.util.Objects;


public class TarihBugundenBuyukOlmaliValidator implements ConstraintValidator<TarihBugundenBuyukOlmali, LocalDateTime> {

  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    return Objects.isNull(value) || value.isAfter(LocalDateTime.now().toLocalDate().atStartOfDay().plusSeconds(-1));
  }


}
