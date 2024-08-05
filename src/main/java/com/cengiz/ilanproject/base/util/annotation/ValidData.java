package com.cengiz.ilanproject.base.util.annotation;


import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidData<T> {

  @Valid
  private T data;
}
