package com.cengiz.ilanproject.modules.kys.data.mapper;


import org.mapstruct.Mapper;

import com.cengiz.ilanproject.base.mapper.MapperBase;
import com.cengiz.ilanproject.modules.kys.data.dto.KullaniciDto;
import com.cengiz.ilanproject.modules.kys.data.entity.Kullanici;


/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@Mapper
public interface KullaniciMapper extends MapperBase<Kullanici, KullaniciDto> {

}
