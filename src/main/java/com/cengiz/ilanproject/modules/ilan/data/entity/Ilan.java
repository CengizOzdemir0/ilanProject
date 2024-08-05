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
@Table(name = "ilan", schema = "genel")
@Getter
@Setter
public class Ilan extends BaseDto {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "adi")
  private String adi;
  @Column(name = "metin")
  private String metin;
  @Column(name = "fiyat")
  private Long fiyat;
  @Column(name = "goruntulenme_sayisi")
  private Integer goruntulenmeSayisi;

  @Column(name = "aktif")
  private Boolean aktif;

  @Column(name = "kaydeden_kullanici_id")
  private Integer kaydedenKullaniciId;
  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani = LocalDateTime.now();

  @Column(name = "guncelleyen_kullanici_id")
  private Integer guncelleyenKullaniciId;
  @Column(name = "guncelleme_zamani")
  private LocalDateTime guncellemeZamani;


}
