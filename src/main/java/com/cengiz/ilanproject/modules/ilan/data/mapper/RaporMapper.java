package com.cengiz.ilanproject.modules.ilan.data.mapper;


import org.mapstruct.Mapper;

import com.cengiz.ilanproject.base.mapper.MapperBase;
import com.cengiz.ilanproject.modules.ilan.data.dto.RaporDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.Rapor;

@Mapper
public interface RaporMapper extends MapperBase<Rapor, RaporDto> {

}
