package com.cengiz.ilanproject.modules.kys.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@Entity
@Table(name = "kullanici", schema = "genel")
@Getter
@Setter
public class Kullanici {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "adi", length = 150)
  private String adi;

  @Column(name = "soyadi", length = 150)
  private String soyadi;

  @Column(name = "telefon")
  private Long telefon;

  @Column(name = "eposta")
  private String eposta;

  @Column(name = "parola")
  private String parola;

  @Column(name = "kullaniciTipi")
  private Integer kullaniciTipi;


  @Column(name = "aktif")
  private Boolean aktif = true;

  @Column(name = "kayit_zamani")
  private LocalDateTime kayitZamani = LocalDateTime.now();




}
