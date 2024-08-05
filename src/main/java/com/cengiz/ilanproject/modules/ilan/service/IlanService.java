package com.cengiz.ilanproject.modules.ilan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.cengiz.ilanproject.modules.ilan.data.dto.IlanDto;
import com.cengiz.ilanproject.modules.ilan.data.dto.YoneticiIlanOnayDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;

public interface IlanService {

  @Transactional
  IlanDto save(IlanDto ilan);

  @Transactional
  IlanDto update(IlanDto ilanDto);

  @Transactional
  IlanDto goruntuleme(IlanDto ilanDto);

  IlanDto getById(IlanDto ilanDto);

  List<IlanDto> getAll();

  @Transactional
  IlanDto ilanOnayla(YoneticiIlanOnayDto dto);

  // Yönetici pasif ilan sorgulama
  List<IlanDto> getPasifIlanList();

  // Onaylanmış son 10 ilan
  List<IlanDto> getOnaylanmisSon10ilan();

  Optional<Ilan> findById(Long id);
}
