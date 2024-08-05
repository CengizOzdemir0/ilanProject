package com.cengiz.ilanproject.modules.ilan.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.cengiz.ilanproject.base.dto.BaseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YoneticiIlanOnayDto extends BaseDto {

  private Long fkIlanId;
   /* 1 = onay bekliyor
  2 = onaylandı
  3 = red edildi  */
  private Integer onayDurumu;
  private Integer islemYapanKullaniciId;

}
