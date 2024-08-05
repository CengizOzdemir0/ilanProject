package com.cengiz.ilanproject.modules.ilan.service.impl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cengiz.ilanproject.modules.ilan.data.dto.RaporDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;
import com.cengiz.ilanproject.modules.ilan.data.entity.Rapor;
import com.cengiz.ilanproject.modules.ilan.data.mapper.RaporMapper;
import com.cengiz.ilanproject.modules.ilan.repository.IRaporRepository;
import com.cengiz.ilanproject.modules.ilan.service.IlanService;
import com.cengiz.ilanproject.modules.ilan.service.RaporService;
import com.cengiz.ilanproject.modules.kys.data.entity.Kullanici;
import com.cengiz.ilanproject.modules.kys.service.KullaniciService;

@Service
@RequiredArgsConstructor
public class RaporServiceImpl implements RaporService {

  private final RaporMapper raporMapper;
  private final IRaporRepository raporRepository;
  private final IlanService ilanService;
  private final KullaniciService kullaniciService;

  @Override
  public RaporDto save(RaporDto raporDto) {

    Rapor rapor = raporMapper.dtoToEntity(raporDto);
    rapor.setKayitZamani(LocalDateTime.now());
    rapor.setKaydedenKullaniciId(1); // TODO  KullanıcıHelperdan da alınabilir
    return raporMapper.entityToDto(raporRepository.save(rapor));
  }


  @Override
  public RaporDto raporGoruntume(Long ilanId) {
    Optional<Rapor> rapor = raporRepository.findById(ilanId);
    Optional<Ilan> ilan = ilanService.findById(ilanId);
   Optional<Kullanici> kullanici = kullaniciService.findKullaniciId(ilanId);
    Rapor resRapor = new Rapor();
    String raporMetni = null;
    if (rapor.isPresent() && ilan.isPresent() && kullanici.isPresent()) {
      resRapor = rapor.get();
      raporMetni = createRaporMetni(ilan.get().getAdi(),kullanici.get().getAdi(),ilan.get().getKayitZamani(),ilan.get().getGoruntulenmeSayisi());
    }

    resRapor.setRapor(raporMetni);
    return raporMapper.entityToDto(raporRepository.save(resRapor));

  }



  public String createRaporMetni(String ilanAdi, String kullaniciAdi, LocalDateTime ilanOlusturulmaTarihi, int goruntulenmeSayisi) {
    long gunFarki = ChronoUnit.DAYS.between(ilanOlusturulmaTarihi, LocalDateTime.now());
    return String.format(
        "%s ilanı %s kullanıcısı tarafından %d gün önce oluşturulmuştur. İlan %d kere görüntülenmiştir.",
        ilanAdi, kullaniciAdi, gunFarki, goruntulenmeSayisi
    );
  }



}
