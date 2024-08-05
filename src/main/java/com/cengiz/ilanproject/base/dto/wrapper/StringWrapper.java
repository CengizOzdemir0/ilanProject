package com.cengiz.ilanproject.base.dto.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.cengiz.ilanproject.base.dto.BaseDto;
/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@AllArgsConstructor
@Getter
public final class StringWrapper extends BaseDto {

  private final String value;
}
