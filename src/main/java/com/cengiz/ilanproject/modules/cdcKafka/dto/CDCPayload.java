package com.cengiz.ilanproject.modules.cdcKafka.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */
@Data
@ToString
public class CDCPayload {

  private Map<String, Object> before;
  private Map<String, Object> after;
  private CDCDurumTipleri op;

  @JsonProperty("ts_ms")
  private Timestamp tsMs;
}
