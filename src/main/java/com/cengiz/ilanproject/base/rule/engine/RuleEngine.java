package com.cengiz.ilanproject.base.rule.engine;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.config.domain.RestResponse;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Slf4j
public class RuleEngine {

  private List<RuleResult> ruleResults = new ArrayList<>();

  private boolean stopOnerror = true;

  private List<IRule> rules = new ArrayList<>();

  public RuleEngine(List<IRule> rules) {
    this.rules = rules;
  }

  public RuleEngine(IRule... rules) {
    Collections.addAll(this.rules, rules);
  }

  public static RuleEngine build() {
    return new RuleEngine();
  }

  public static RuleEngine build(IRule... rules) {
    return new RuleEngine(rules);
  }

  public static RuleEngine build(List<IRule> rules) {
    return new RuleEngine(rules);
  }


  public RuleEngine register(IRule rule) {
    Objects.requireNonNull(rule);
    this.rules.add(rule);
    return this;
  }

  public RuleEngine unregister(IRule rule) {
    Objects.requireNonNull(rule);
    this.rules.remove(rule);
    return this;
  }

  public RuleEngine unregister(final String ruleName) {
    Objects.requireNonNull(ruleName);
    IRule rule = findRuleByName(ruleName);
    if (rule != null) {
      this.rules.remove(rule);
    }
    return this;
  }

  public boolean isEmpty() {
    return this.rules.isEmpty();
  }

  public void clear() {
    this.rules.clear();
  }

  private IRule findRuleByName(String ruleName) {
    for (IRule rule : rules) {
      if (rule.getName().equalsIgnoreCase(ruleName)) {
        return rule;
      }
    }
    return null;
  }

  public RuleEngine execute() {
    this.ruleResults = new ArrayList<>();
    if (rules.isEmpty()) {
      log.warn("Kural listesi boş");
      return this;
    }
    log(rules);
    log.debug("Kurallar çalıştırlmaya başlandı");
    for (IRule rule : rules) {
      final String name = (rule.getName().equals(IRule.DEFAULT_NAME) ? rule.getClass().getSimpleName() : rule.getName());
      log.debug("Kural : '{}' çalıştırılıyor", name);
      try {
        List<RuleResult> ruleExecuteResults = rule.execute();
        log.debug("Kural '{}' başarılı çalıştı", name);
        if (!ruleExecuteResults.isEmpty()) {
          this.ruleResults.addAll(ruleExecuteResults);
          if (stopOnerror) {
            return this;
          }
        }
      } catch (Exception ex) {
        log.error("Kural '" + name + "' hatalı çalştı", ex);
        this.ruleResults.add(new RuleResult(Mesajlar.GNL_RULE_HATALI_CALISTI));
      }
    }
    return this;
  }

  private void log(List<IRule> rules) {
    log.debug("Kayıtlı kurallar::");
    for (IRule rule : rules) {
      log.debug("Kural { name = '{}', description = '{}'}", rule.getName(), rule.getDescription());
    }
  }

  public RuleEngine setStopOnerror(boolean stopOnerror) {
    this.stopOnerror = stopOnerror;
    return this;
  }

  public boolean hasMesaj(Mesajlar mesajlar) {
    boolean has = false;
    for (RuleResult ruleResult : this.ruleResults) {
      if (ruleResult.getMesajlar().equals(mesajlar)) {
        has = true;
        break;
      }
    }
    return has;
  }

  public boolean hasError() {
    return (!this.ruleResults.isEmpty());
  }

  public List<RuleResult> getRuleResults() {
    return this.ruleResults;
  }
  public RestResponse getRestReponse() {
    if (!hasError()) {
      return new RestResponse().createOk();
    }
    RestResponse restResponse = new RestResponse<>();
    for (RuleResult ruleResult : this.ruleResults) {
      if (ruleResult.getMesajlar() != null) {
        restResponse.createWithMesajEnum(ruleResult.getMesajlar());
      }
    }
    return restResponse;
  }

  public Mesajlar getRestReponseMesaj() {
    for (RuleResult ruleResult : this.ruleResults) {
      if (ruleResult.getMesajlar() != null) {
       return ruleResult.getMesajlar();
      }
    }
    return null;
  }


}
