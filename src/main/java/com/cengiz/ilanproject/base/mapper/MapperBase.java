package com.cengiz.ilanproject.base.mapper;



import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.cengiz.ilanproject.base.util.CommonUtility;


/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

public interface MapperBase<E, D> {

  @Named("entityToDtoCycle")
  D entityToDto(E entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

  default D entityToDto(E entity) {
    return entityToDto(entity, CommonUtility.cycleAvoidingMappingContext);
  }


  @InheritInverseConfiguration
  @Named("dtoToEntityCycle")
  E dtoToEntity(D dto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

  default E dtoToEntity(D dto) {
    return dtoToEntity(dto, CommonUtility.cycleAvoidingMappingContext);
  }

  default List<D> dtoToEntity(final List<E> entity, final @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) {
    List<D> listDto = new ArrayList<>();
    entity.forEach(e -> listDto.add(entityToDto(e, cycleAvoidingMappingContext)));
    return listDto;
  }

  default List<D> entityToDto(final List<E> entity) {
    List<D> listDto = new ArrayList<>();
    entity.forEach(e -> listDto.add(entityToDto(e, CommonUtility.cycleAvoidingMappingContext)));
    return listDto;
  }

  default List<E> listDtoToListEntity(final List<D> dto, final @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) {
    List<E> listEntity = new ArrayList<>();
    dto.forEach(d -> listEntity.add(dtoToEntity(d, cycleAvoidingMappingContext)));
    return listEntity;
  }

  default List<E> listDtoToListEntity(final List<D> dto) {
    List<E> listEntity = new ArrayList<>();
    dto.forEach(d -> listEntity.add(dtoToEntity(d, CommonUtility.cycleAvoidingMappingContext)));
    return listEntity;
  }

  void dtoToEntity(@MappingTarget E e, D d);

}
