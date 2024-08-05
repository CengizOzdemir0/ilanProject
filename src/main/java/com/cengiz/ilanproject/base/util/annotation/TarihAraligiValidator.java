package com.cengiz.ilanproject.base.util.annotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;


public class TarihAraligiValidator implements ConstraintValidator<TarihAraligi, Object> {

  private String beforeFieldName;
  private String afterFieldName;

  @Override
  public void initialize(TarihAraligi constraintAnnotation) {
    beforeFieldName = constraintAnnotation.before();
    afterFieldName = constraintAnnotation.after();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    try {
      final Field beforeDateField = value.getClass().getDeclaredField(beforeFieldName);
      beforeDateField.setAccessible(true);

      final Field afterDateField = value.getClass().getDeclaredField(afterFieldName);
      afterDateField.setAccessible(true);

      final LocalDateTime beforeDate = (LocalDateTime) beforeDateField.get(value);
      final LocalDateTime afterDate = (LocalDateTime) afterDateField.get(value);

      return Objects.isNull(beforeDate) || Objects.isNull(afterDate) || beforeDate.isBefore(afterDate);

    } catch (NoSuchFieldException | IllegalAccessException ignored) {
      return false;
    }
  }


}
