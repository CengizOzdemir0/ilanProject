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
public class IlanOnayDto extends BaseDto {



  private Long id;

  private Long fkIlanId;

  private Integer onayDurumu;

  private Integer onaylayanKullaniciId;

  private LocalDateTime kayitZamani;

  private LocalDateTime guncellemeZamani;
}
