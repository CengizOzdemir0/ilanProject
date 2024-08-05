package com.cengiz.ilanproject.config.domain;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Component
@Slf4j
public class BeanUtilContextInitializer {

  private final ApplicationContext context;

  public BeanUtilContextInitializer(ApplicationContext context) {
    this.context = context;
  }

  @PostConstruct
  public void init() {
    log.info("Setting application context in config.domain.BeanUtil");
    BeanUtil.setApplicationContext(context);
  }
}