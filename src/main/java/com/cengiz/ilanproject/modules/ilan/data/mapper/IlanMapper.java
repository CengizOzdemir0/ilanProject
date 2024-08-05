package com.cengiz.ilanproject.modules.ilan.data.mapper;

import org.mapstruct.Mapper;

import com.cengiz.ilanproject.base.mapper.MapperBase;
import com.cengiz.ilanproject.modules.ilan.data.dto.IlanDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;

@Mapper
public interface IlanMapper extends MapperBase<Ilan, IlanDto> {

}
