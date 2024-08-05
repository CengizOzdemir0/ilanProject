package com.cengiz.ilanproject.modules.ilan.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.cengiz.ilanproject.base.dto.BaseDto;

@Entity
@Table(name = "ilan_onay", schema = "genel")
@Getter
@Setter
public class IlanOnay extends BaseDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "fk_ilan_id")
  private Long fkIlanId;
  @Column(name = "onay_durumu")
  private Integer onayDurumu;
  @Column(name = "onaylayan_kullanici_id")
  private Integer onaylayanKullaniciId;
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani;
  @Column(name = "guncelleme_zamani")
  private LocalDateTime guncellemeZamani;

}
