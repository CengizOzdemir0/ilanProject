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
public class RaporDto extends BaseDto {

  private Long id;

  private Long fkIlanId;
  private String rapor;
  private Integer kaydedenKullaniciId;
  private LocalDateTime kayitZamani = LocalDateTime.now();
  private Integer guncelleyenKullaniciId;
  private LocalDateTime guncellemeZamani;


}
