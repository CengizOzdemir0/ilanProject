package com.cengiz.ilanproject.modules.kys.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.cengiz.ilanproject.base.dto.BaseDto;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciDto extends BaseDto {

  private Long id;
  private String adi;
  private String soyadi;
  private Integer kullaniciTipi;
  private Long telefon;
  private String eposta;
  private String parola;
  private Boolean aktif = true;
  private LocalDateTime kayitZamani = LocalDateTime.now();

}
