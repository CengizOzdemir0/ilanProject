package com.cengiz.ilanproject.base.tipler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */


@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Mesajlar {
  GNL_KAYIT_BULUNAMADI(HttpStatus.NO_CONTENT, "GNL1005", MesajSeviye.INFO),
  GNL_ISLEM_BASARILI(HttpStatus.OK, "GNL1009", MesajSeviye.INFO),
  GNL_BEKLENMEYEN_HATA_OLUSTU(HttpStatus.INTERNAL_SERVER_ERROR, "GNL1000", MesajSeviye.ERROR),
  GNL_RULE_HATALI_CALISTI(HttpStatus.BAD_REQUEST, "GNL1019", MesajSeviye.ERROR),
  VLD_CEP_TELEFON_FORMAT(HttpStatus.BAD_REQUEST, "VLD1019", MesajSeviye.ERROR),
  GNL_AYNI_KAYIT_MEVCUT(HttpStatus.BAD_REQUEST, "VLD1000", MesajSeviye.ERROR),
  VTN_KAYIT_BULUNAMADI(HttpStatus.BAD_REQUEST, "GNL1025", MesajSeviye.ERROR);

  private final HttpStatus httpStatus;
  private final String kodu;
  private final MesajSeviye seviye;

  public static Mesajlar of(String kodu) {
    for (Mesajlar inam : values()) {
      if (inam.kodu.equals(kodu) || inam.name().equals(kodu)) {
        return inam;
      }
    }
    return GNL_BEKLENMEYEN_HATA_OLUSTU;
  }

}