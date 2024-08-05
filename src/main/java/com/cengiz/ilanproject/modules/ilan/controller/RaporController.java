package com.cengiz.ilanproject.modules.ilan.controller;


import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengiz.ilanproject.config.domain.ResponseHelper;
import com.cengiz.ilanproject.config.domain.RestResponse;
import com.cengiz.ilanproject.modules.ilan.data.dto.RaporDto;
import com.cengiz.ilanproject.modules.ilan.service.RaporService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 10:08
 */

@RestController
@RequestMapping("/rapor")
@RequiredArgsConstructor
public class RaporController {

  private final RaporService raporService;



  @PostMapping("/goruntuleme/{ilanId}")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<RaporDto>> ilanRapor(@PathVariable @NotNull Long ilanId) {
    return ResponseHelper.responseEntityOkFromData(raporService.raporGoruntume(ilanId));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<RaporDto>> save(@RequestBody  RaporDto data) {
    return ResponseHelper.responseEntityOkFromData(raporService.save(data));
  }


}
