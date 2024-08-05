package com.cengiz.ilanproject.base.rule.engine;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

public interface IRule {

  /**
   * İş kuralın varsayılan ismi
   */
  String DEFAULT_NAME = "rule";
  /**
   * İş kuralın açıklaması
   */
  String DEFAULT_DESCRIPTION = "description";

  /**
   * İş kuralın adını getirir
   *
   * @return iş kuralın adı
   */
  String getName();

  /**
   * İş kuralın açıklmasını getirir
   *
   * @return iş kuralı açıklması
   */
  String getDescription();

  /**
   * İş kuralın çalışmasını yapar
   */
  List<RuleResult> execute();

}
