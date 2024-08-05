package com.cengiz.ilanproject.config.exception;

import lombok.Getter;

import java.util.Map;

import com.cengiz.ilanproject.base.tipler.Mesajlar;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Getter
public class IlanException extends RuntimeException {


  private final Mesajlar mesajlarEnum;
  private final transient Map<String, Object> mesajArgsMap;

  public IlanException(Mesajlar mesajlarEnum) {
    this(mesajlarEnum, null);
  }

  public IlanException(Mesajlar mesajlarEnum, Map<String, Object> mesajArgsMap) {
    this.mesajlarEnum = mesajlarEnum;
    this.mesajArgsMap = mesajArgsMap;
  }

}
