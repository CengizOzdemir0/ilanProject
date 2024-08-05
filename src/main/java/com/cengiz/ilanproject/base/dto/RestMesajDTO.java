package com.cengiz.ilanproject.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestMesajDTO implements Serializable {

  private String kodu;
  private String mesaj;

}
