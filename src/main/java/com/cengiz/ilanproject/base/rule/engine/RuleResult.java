package com.cengiz.ilanproject.base.rule.engine;

import lombok.Data;


import com.cengiz.ilanproject.base.tipler.Mesajlar;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Data
public class RuleResult {
  private Mesajlar mesajlar;

  public RuleResult(Mesajlar mesajlar) {
    this.mesajlar = mesajlar;
  }


}
