package com.cengiz.ilanproject.modules.kys.service.impl;


import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.config.exception.IlanException;
import com.cengiz.ilanproject.modules.kys.data.dto.KullaniciDto;
import com.cengiz.ilanproject.modules.kys.data.entity.Kullanici;
import com.cengiz.ilanproject.modules.kys.data.mapper.KullaniciMapper;
import com.cengiz.ilanproject.modules.kys.repository.KullaniciRepository;
import com.cengiz.ilanproject.modules.kys.service.KullaniciService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@Service
@RequiredArgsConstructor
@Transactional
public class KullaniciServiceImpl implements KullaniciService {

  private final KullaniciRepository kullaniciRepository;
  private final KullaniciMapper kullaniciapper;
  private final PasswordEncoder passwordEncoder;



  @Override
  public KullaniciDto save(KullaniciDto dto) {
    // TODO telefon ve mail adresi geçerliliği kontrol edilebilir
    // Password kontrol eldilebilir min 8 karakter olacak

    boolean telefonKontrol = kullaniciRepository.existsByTelefon(dto.getTelefon());
    if (telefonKontrol) {
      throw new IlanException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    dto.setKullaniciTipi(2);
    String hashedPassword = passwordEncoder.encode(dto.getParola());
    dto.setParola(hashedPassword);
    return kullaniciapper.entityToDto(kullaniciRepository.save(kullaniciapper.dtoToEntity(dto)));
  }

  @Override
  public KullaniciDto update(KullaniciDto dto) {
    Kullanici kullanici = kullaniciRepository.findByEposta(dto.getEposta());
    if (kullanici == null) {
      throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
    }
    if (dto.getParola() != null && !dto.getParola().isEmpty()) {
      String hashedPassword = passwordEncoder.encode(dto.getParola());
      dto.setParola(hashedPassword);
    }
    return kullaniciapper.entityToDto(kullaniciRepository.save(kullaniciapper.dtoToEntity(dto)));
  }

  @Override
  public KullaniciDto getBytelefon(Long telefon) {
    return kullaniciapper.entityToDto(kullaniciRepository.findByTelefon(telefon));
  }

  @Override
  public Optional<Kullanici> findKullaniciId(Long id) {
    return kullaniciRepository.findById(id);
  }


}
