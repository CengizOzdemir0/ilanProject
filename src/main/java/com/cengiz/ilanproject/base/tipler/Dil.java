package com.cengiz.ilanproject.base.tipler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Getter
@AllArgsConstructor
public enum Dil {
  TR(1, "tr", "TR", "tr-TR", "Türkçe"),
  EN(2, "en", "US", "en-US", "İngilizce"),
  AR(3, "ar", "SA", "ar-SA", "Arapça"),
  RU(4, "ru", "RU", "ru-RU", "Rusça"),
  DE(5, "de", "DE", "de-DE", "Almanca");

  private final int val;
  private final String kod;
  private final String ulke;
  private final String ulkeKodu;
  private final String adi;
}