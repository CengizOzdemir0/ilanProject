package com.cengiz.ilanproject.modules.ilan.data.mapper;

import org.mapstruct.Mapper;

import com.cengiz.ilanproject.base.mapper.MapperBase;
import com.cengiz.ilanproject.modules.ilan.data.dto.IlanOnayDto;
import com.cengiz.ilanproject.modules.ilan.data.entity.IlanOnay;


@Mapper
public interface IlanOnayMapper  extends MapperBase<IlanOnay, IlanOnayDto> {

}
