package com.cengiz.ilanproject.modules.kys.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengiz.ilanproject.config.domain.ResponseHelper;
import com.cengiz.ilanproject.config.domain.RestResponse;
import com.cengiz.ilanproject.modules.kys.data.dto.KullaniciDto;
import com.cengiz.ilanproject.modules.kys.service.KullaniciService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@RestController
@RequestMapping("/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

  private final KullaniciService vatandasService;

  @PostMapping
  public ResponseEntity<RestResponse<KullaniciDto>> save(@RequestBody KullaniciDto data) {
    return ResponseHelper.responseEntityOkFromData(vatandasService.save(data));
  }

  @PutMapping
  public ResponseEntity<RestResponse<KullaniciDto>> update(@RequestBody KullaniciDto data) {
    return ResponseHelper.responseEntityOkFromData(vatandasService.update(data));
  }



}
