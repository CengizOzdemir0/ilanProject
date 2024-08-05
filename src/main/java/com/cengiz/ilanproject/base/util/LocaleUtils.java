package com.cengiz.ilanproject.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleUtils {

  public static List<Locale> getList() {
    return Arrays.asList(new Locale("tr", "TR"), new Locale("en", "US"), new Locale("ar", "AE"), new Locale("ru", "RU"), new Locale("de", "DE"));
  }

  public static Locale getDefault() {
    return new Locale("tr", "TR");
  }

  /**
   * Request call yapıldığı zaman headerdaki locale değerini getirir
   *
   * @return
   */
  public static Locale getFromHeader() {
    return LocaleContextHolder.getLocale();
  }
}