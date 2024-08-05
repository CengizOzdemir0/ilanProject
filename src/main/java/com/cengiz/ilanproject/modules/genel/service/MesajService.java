package com.cengiz.ilanproject.modules.genel.service;



import java.util.List;

import com.cengiz.ilanproject.base.dto.wrapper.BooleanWrapper;
import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.modules.genel.data.dto.MesajDto;


public interface MesajService {


  MesajDto save(MesajDto dto);
  MesajDto update(Long id, MesajDto dto);
  BooleanWrapper delete(Long id, MesajDto dto);
  MesajDto getMesajByMesajEnum(Mesajlar mesajlarEnum);
  List<MesajDto> getAll();
  MesajDto getById(Long id);
}
