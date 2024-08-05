package com.cengiz.ilanproject.modules.ilan.controller;


import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengiz.ilanproject.config.domain.ResponseHelper;
import com.cengiz.ilanproject.config.domain.RestResponse;
import com.cengiz.ilanproject.modules.ilan.data.dto.IlanDto;
import com.cengiz.ilanproject.modules.ilan.data.dto.YoneticiIlanOnayDto;
import com.cengiz.ilanproject.modules.ilan.service.IlanService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@RestController
@RequestMapping("/ilan")
@RequiredArgsConstructor
public class IlanController {

  private final IlanService ilanService;

  // ilan verme
  @PostMapping
  @PreAuthorize("hasAuthority('KULLANICI')")
  public ResponseEntity<RestResponse<IlanDto>> save(@RequestBody IlanDto data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // Kullanıcı bilgileri principal dan alınabilir
    Object principal = authentication.getPrincipal();
    return ResponseHelper.responseEntityOkFromData(ilanService.save(data));
  }
  // ilan güncelleme
  @PutMapping
  @PreAuthorize("hasAuthority('KULLANICI')")
  public ResponseEntity<RestResponse<IlanDto>> update(@RequestBody IlanDto data) {
    return ResponseHelper.responseEntityOkFromData(ilanService.update(data));
  }
  @GetMapping
  @PreAuthorize("hasAuthority('KULLANICI')")
  public ResponseEntity<RestResponse<IlanDto>> getById(@RequestBody IlanDto data) {
    return ResponseHelper.responseEntityOkFromData(ilanService.getById(data));
  }
  // ilan görüntüleme
  @PostMapping("/goruntuleme")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<IlanDto>> goruntuleme(@RequestBody IlanDto data) {
    return ResponseHelper.responseEntityOkFromData(ilanService.goruntuleme(data));
  }

  // ilan onaylama
  // PreAutoruzotion yapılacak
  @PostMapping("/onaylama")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<IlanDto>> ilanOnayla(@RequestBody YoneticiIlanOnayDto data) {
    return ResponseHelper.responseEntityOkFromData(ilanService.ilanOnayla(data));
  }

  // Yönetici için pasif ilan list
  @GetMapping("/pasif/ilanlar")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<IlanDto>> pasifIlanList() {
    return ResponseHelper.responseEntityOkFromListData(ilanService.getPasifIlanList());
  }

  //Onaylanmış son 10 ilan
  @GetMapping("/onaylanmis/son/ilanlar")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<IlanDto>> getOnaylanmisSon10ilan() {
    return ResponseHelper.responseEntityOkFromListData(ilanService.getOnaylanmisSon10ilan());
  }



}
