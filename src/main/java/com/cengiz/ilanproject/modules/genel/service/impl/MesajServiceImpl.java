package com.cengiz.ilanproject.modules.genel.service.impl;


import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.Optional;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cengiz.ilanproject.base.dto.wrapper.BooleanWrapper;
import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.config.exception.IlanException;
import com.cengiz.ilanproject.modules.genel.data.dto.MesajDto;
import com.cengiz.ilanproject.modules.genel.data.entity.Mesaj;
import com.cengiz.ilanproject.modules.genel.data.mapper.MesajMapper;
import com.cengiz.ilanproject.modules.genel.repository.MesajRepository;
import com.cengiz.ilanproject.modules.genel.service.MesajService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 09/03/2024 - 22:08
 */


@Service
@RequiredArgsConstructor
@Transactional
public class MesajServiceImpl implements MesajService {

  private final MesajRepository mesajRepository;
  private final MesajMapper mesajMapper;


  @Override
  public MesajDto save(MesajDto dto) {
    boolean mesajKontrol = mesajRepository.existsByKoduAndAdi(dto.getKodu(), dto.getAdi());
    if (mesajKontrol) {
      throw new IlanException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return mesajMapper.entityToDto(mesajRepository.save(mesajMapper.dtoToEntity(dto)));
  }

  @Override
  public MesajDto update(Long id, MesajDto dto) {
    if (!id.equals(dto.getId())) {
      throw new IlanException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    Optional<Mesaj> mesaj = mesajRepository.findById(dto.getId());
    if (mesaj.isEmpty()) {
      throw new IlanException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    if (!dto.getKodu().equals(mesaj.get().getKodu()) || !dto.getAdi().equals(mesaj.get().getAdi())) {
      Mesaj mesajKontrol = mesajRepository.findByKoduAndAdi(dto.getKodu(), dto.getAdi());
      if (mesajKontrol != null) {
        throw new IlanException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
      }
    }
    if (dto.getAktif().equals(mesaj.get().getAktif()) && dto.getMesaj().equals(mesaj.get().getMesaj())
        && dto.getLhttpStatusId().equals(mesaj.get().getLhttpStatusId()) && dto.getAdi().equals(mesaj.get().getAdi())
        && dto.getKodu().equals(mesaj.get().getKodu()) && dto.getLseviyeTipi().equals(mesaj.get().getLseviyeTipi())) {
      throw new IlanException(Mesajlar.GNL_AYNI_KAYIT_MEVCUT);
    }
    return mesajMapper.entityToDto(mesajRepository.save(mesajMapper.dtoToEntity(dto)));

  }

  @Override
  public BooleanWrapper delete(Long id, MesajDto dto) {
    if (!id.equals(dto.getId())) {
      throw new IlanException(Mesajlar.VTN_KAYIT_BULUNAMADI);
    }
    boolean mesajKontrol = mesajRepository.existsById(dto.getId());
    if (!mesajKontrol) {
      throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
    }
    mesajRepository.deleteByIdAndKodu(dto.getId(), dto.getKodu());
    return new BooleanWrapper(true);
  }


  @Override
  public MesajDto getMesajByMesajEnum(Mesajlar mesajlarEnum) {
    Mesaj mesaj = mesajRepository.findByAdi(mesajlarEnum.name());
    MesajDto mesajDto = new MesajDto();
    mesajDto.setLhttpStatusId(mesaj.getLhttpStatusId());
    mesajDto.setKodu(mesaj.getKodu());
    mesajDto.setMesaj(mesaj.getMesaj());
    mesajDto.setLseviyeTipi(mesaj.getLseviyeTipi());
    return mesajDto;
  }

  @Override
  public List<MesajDto> getAll() {
    return mesajMapper.entityToDto(mesajRepository.findAll());
  }
  @Override
  public MesajDto getById(Long id) {
    return mesajRepository.findById(id)
        .map(mesajMapper::entityToDto)
        .orElseThrow(()-> new IlanException(Mesajlar.VTN_KAYIT_BULUNAMADI));
  }

}
