package com.cengiz.ilanproject.base.rule;


import java.util.List;

import com.cengiz.ilanproject.base.rule.engine.BasicRule;
import com.cengiz.ilanproject.base.rule.engine.RuleResult;

import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.base.util.ValidationUtil;


public class RuleCepTelefonNoGecerliOlmalidir extends BasicRule {

  private Long cepTelefonNo;

  public RuleCepTelefonNoGecerliOlmalidir(Long cepTelefonNo) {
    this.cepTelefonNo = cepTelefonNo;
  }


  @Override
  public List<RuleResult> execute() {
    if (cepTelefonNo == null || !ValidationUtil.cepTelefonValidation(cepTelefonNo)) {
      ruleResults.add(new RuleResult(Mesajlar.VLD_CEP_TELEFON_FORMAT));
    }
    return ruleResults;
  }
}
