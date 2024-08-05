package com.cengiz.ilanproject.modules.ilan.service.impl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.config.exception.IlanException;
import com.cengiz.ilanproject.modules.ilan.data.dto.IlanDto;
import com.cengiz.ilanproject.modules.ilan.data.dto.YoneticiIlanOnayDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;
import com.cengiz.ilanproject.modules.ilan.data.entity.IlanOnay;
import com.cengiz.ilanproject.modules.ilan.data.mapper.IlanMapper;
import com.cengiz.ilanproject.modules.ilan.repository.IlanOnayRepository;
import com.cengiz.ilanproject.modules.ilan.repository.IlanRepository;
import com.cengiz.ilanproject.modules.ilan.service.IlanService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class IlanServiceImpl implements IlanService {

  private final IlanRepository ilanRepository;
  private final IlanOnayRepository ilanOnayRepository;
  private final IlanMapper ilanMapper;


  @Transactional
  @Override
  public IlanDto save(IlanDto ilandto) {
    //TODO veri kontrolleri yapılabilir
    Ilan ilan =ilanMapper.dtoToEntity(ilandto);
    ilan.setKaydedenKullaniciId(ilandto.getIslemYapanKullaniciId());

    Ilan res = ilanRepository.save(ilan);
    IlanOnay ilanOnay = new IlanOnay();
    ilanOnay.setFkIlanId(res.getId());
    ilanOnay.setOnayDurumu(1);//Onay bekliyor durumunda
    ilanOnay.setKayitZamani(LocalDateTime.now());
    ilanOnayRepository.save(ilanOnay);
    return ilanMapper.entityToDto(res);
  }

  @Transactional
  @Override
  public IlanDto update(IlanDto ilanDto) {
      ilanRepository.findById(ilanDto.getId()).orElseThrow(()
        -> new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI));
    Ilan ilan =ilanMapper.dtoToEntity(ilanDto);
    ilan.setGuncelleyenKullaniciId(ilanDto.getIslemYapanKullaniciId());
    return ilanMapper.entityToDto(ilanRepository.save(ilan));

  }

  @Override
  @Transactional
  public IlanDto goruntuleme(IlanDto ilanDto) {
    Optional<Ilan> ilan = ilanRepository.findById(ilanDto.getId());
    Ilan res = new Ilan();
    if (ilan.isPresent()) {
      res = ilan.get();
      res.setGoruntulenmeSayisi(res.getGoruntulenmeSayisi() + 1);
    }

    return ilanMapper.entityToDto(ilanRepository.save(res));
  }

  @Override
  public IlanDto getById(IlanDto ilanDto) {
    Ilan ilan = ilanRepository.getReferenceById(ilanDto.getId());
    if(ilan == null) {
      throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
    }
    return ilanMapper.entityToDto(ilan);
  }

  @Override
  public List<IlanDto> getAll() {
    return ilanMapper.entityToDto(ilanRepository.findAll());
  }


 // yöneticinin onaylama ve redetme durumunu yap

  @Override
  @Transactional
  public IlanDto ilanOnayla(YoneticiIlanOnayDto dto) {
    Ilan ilan = ilanRepository.getReferenceById(dto.getFkIlanId());
    if(ilan == null) {
      throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
    }
    IlanOnay ilanOnay = ilanOnayRepository.findByFkIlanId(dto.getFkIlanId());
    ilanOnay.setOnayDurumu(dto.getOnayDurumu());
    ilanOnay.setOnaylayanKullaniciId(dto.getIslemYapanKullaniciId());
    ilanOnay.setGuncellemeZamani(LocalDateTime.now());
    ilanOnayRepository.save(ilanOnay);
    // 2 onaylamışsa
    if(dto.getOnayDurumu() == 2) {
      ilan.setAktif(true);
    } else {
      ilan.setAktif(false);
    }
    ilanRepository.save(ilan);
    return ilanMapper.entityToDto(ilan);
  }

  // yöneticinin ilanları sorgulama servisi

  // Yönetici pasif ilan sorgulama
  @Override
  public List<IlanDto> getPasifIlanList() {
    return ilanMapper.entityToDto(ilanRepository.findByAktifFalse());
  }

  // Onaylanmış son 10 ilan
  @Override
  public List<IlanDto> getOnaylanmisSon10ilan() {
    return ilanMapper.entityToDto(ilanRepository.onaylanmisSonİlanlar());
  }

  @Override
  public Optional<Ilan> findById(Long id) {
    return ilanRepository.findById(id);
  }




}
