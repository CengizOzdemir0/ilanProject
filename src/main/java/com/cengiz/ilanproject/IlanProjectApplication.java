package com.cengiz.ilanproject;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class IlanProjectApplication  {

  public static void main(String[] args) {
    SpringApplication.run(IlanProjectApplication.class, args);
  }


}
