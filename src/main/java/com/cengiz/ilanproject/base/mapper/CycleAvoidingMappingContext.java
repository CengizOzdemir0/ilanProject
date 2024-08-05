package com.cengiz.ilanproject.base.mapper;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

public class CycleAvoidingMappingContext {
  private Map<Object, Object> knownInstances = new IdentityHashMap<>();

  @BeforeMapping
  public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
    return (T) knownInstances.get(source);
  }

  @BeforeMapping
  public void storeMappedInstance(Object source, @MappingTarget Object target) {
    knownInstances.put(source, target);
  }
}
