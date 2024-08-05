package com.cengiz.ilanproject.config.domain;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("Spring Boot Uygulaması Ayaklandı....");
  }
}