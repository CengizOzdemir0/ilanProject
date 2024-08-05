package com.cengiz.ilanproject.modules.ilan.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.cengiz.ilanproject.base.dto.BaseDto;

@Entity
@Table(name = "rapor", schema = "genel")
@Getter
@Setter
public class Rapor extends BaseDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "fk_ilan_id")
  private Long fkIlanId;
  @Column(name = "rapor")
  private String rapor;

  @Column(name = "kaydeden_kullanici_id")
  private Integer kaydedenKullaniciId;
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani = LocalDateTime.now();

  @Column(name = "guncelleyen_kullanici_id")
  private Integer guncelleyenKullaniciId;
  @Column(name = "guncelleme_zamani")
  private LocalDateTime guncellemeZamani;

}
