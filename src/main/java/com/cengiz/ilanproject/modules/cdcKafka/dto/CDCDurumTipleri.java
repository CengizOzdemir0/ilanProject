package com.cengiz.ilanproject.modules.cdcKafka.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonCreator;
/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@RequiredArgsConstructor
@Getter
public enum CDCDurumTipleri {

  INSERT("c"),
  UPDATE("u"),
  DELETE("d");

  private final String cdcTip;

  @JsonCreator
  public static CDCDurumTipleri fromCode(String code) {
    for (CDCDurumTipleri operationType : values()) {
      if (operationType.cdcTip.equals(code) || operationType.name().equals(code)) {
        return operationType;
      }
    }
    throw new IllegalArgumentException("Invalid operation code: " + code);
  }
}
