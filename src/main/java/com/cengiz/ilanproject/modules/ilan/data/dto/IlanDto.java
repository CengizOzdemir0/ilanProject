package com.cengiz.ilanproject.modules.ilan.data.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.cengiz.ilanproject.base.dto.BaseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IlanDto extends BaseDto {


  private Long id;
  private String adi;
  private String metin;
  private Long fiyat;
  private Integer goruntulenmeSayisi = 0;
  private Boolean aktif;
  private LocalDateTime kayitZamani = LocalDateTime.now();
  private LocalDateTime guncellemeZamani;
  private Integer islemYapanKullaniciId;

}
