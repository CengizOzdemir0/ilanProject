package com.cengiz.ilanproject.modules.cdcKafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cengiz.ilanproject.modules.cdcKafka.dto.CDCDurumTipleri;
import com.cengiz.ilanproject.modules.cdcKafka.dto.CDCKafkaDto;
import com.cengiz.ilanproject.modules.cdcKafka.dto.CDCPayload;
import com.cengiz.ilanproject.modules.ilan.data.dto.IlanOnayDto;
import com.cengiz.ilanproject.modules.ilan.data.dto.RaporDto;
import com.cengiz.ilanproject.modules.ilan.service.RaporService;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class IlanOnayCDCListener {

  private final ObjectMapper objectMapper;
 private final RaporService raporService;


  @KafkaListener(topics = "genel.ilan_onay", containerFactory = "cdcTemplate")
  public void ahKurumCDCListener(CDCKafkaDto ilanOnayBilgisiCDCDto) {
    CDCPayload ilanOnayBilgisiDtoPayload = ilanOnayBilgisiCDCDto.getPayload();
    if (Objects.isNull(ilanOnayBilgisiDtoPayload) || Objects.isNull(ilanOnayBilgisiDtoPayload.getOp())) {
      log.warn("payload boş geldi :{} ", ilanOnayBilgisiCDCDto);
      return;
    }
    IlanOnayDto ilanOnayDtoAfter = objectMapper.convertValue(ilanOnayBilgisiDtoPayload.getAfter(), IlanOnayDto.class);

    if (CDCDurumTipleri.INSERT.equals(ilanOnayBilgisiDtoPayload.getOp()) && ilanOnayDtoAfter.getOnayDurumu().equals(2)) {
      RaporDto raporDto = new RaporDto();
      raporDto.setFkIlanId(ilanOnayDtoAfter.getFkIlanId());
      raporDto.setRapor("%s ilanı %s kullanıcısı tarafından %d gün önce oluşturulmuştur. İlan %d kere görüntülenmiştir.");
      raporDto.setKayitZamani(LocalDateTime.now());
      raporDto.setKaydedenKullaniciId(1); // 1 Sistem
      raporService.save(raporDto);
    }
  }
}
