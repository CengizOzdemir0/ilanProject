package com.cengiz.ilanproject.modules.genel.controller;


import lombok.RequiredArgsConstructor;


import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengiz.ilanproject.base.dto.wrapper.BooleanWrapper;
import com.cengiz.ilanproject.config.domain.ResponseHelper;
import com.cengiz.ilanproject.config.domain.RestResponse;
import com.cengiz.ilanproject.modules.genel.data.dto.MesajDto;
import com.cengiz.ilanproject.modules.genel.service.MesajService;

/**
 * @author Cengiz ÖZDEMİR
 * @created 05/08/2024 - 13:08
 */

@RestController
@RequestMapping("/mesajlar")
@RequiredArgsConstructor
public class MesajController {

  private final MesajService mesajService;



  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<MesajDto>> getById(
      @PathVariable(value = "id") @NotNull Long id) {
    return ResponseHelper.responseEntityOkFromData(mesajService.getById(id));
  }


  @GetMapping
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<MesajDto>> getAll() {
    return ResponseHelper.responseEntityOkFromListData(mesajService.getAll());
  }


  @PostMapping
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<MesajDto>> save(@RequestBody MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.save(data));
  }


  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<MesajDto>> update(
      @PathVariable(value = "id") @NotNull Long id, @RequestBody MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.update(id, data));
  }



  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('YONETICI')")
  public ResponseEntity<RestResponse<BooleanWrapper>> delete(Long id, MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.delete(id, data));
  }
}
