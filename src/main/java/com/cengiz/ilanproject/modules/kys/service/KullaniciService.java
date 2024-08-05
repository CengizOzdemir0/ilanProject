package com.cengiz.ilanproject.modules.kys.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cengiz.ilanproject.modules.kys.data.dto.KullaniciDto;
import com.cengiz.ilanproject.modules.kys.data.entity.Kullanici;

public interface KullaniciService {

  KullaniciDto save(KullaniciDto dto);

  KullaniciDto update(KullaniciDto dto);
  KullaniciDto getBytelefon(Long telefon);

  Optional<Kullanici> findKullaniciId(Long id);
}
