package com.cengiz.ilanproject.config.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cengiz.ilanproject.base.dto.BaseDto;
import com.cengiz.ilanproject.base.dto.RestMesajDTO;
import com.cengiz.ilanproject.base.tipler.Dil;
import com.cengiz.ilanproject.base.tipler.HttpStatus;
import com.cengiz.ilanproject.base.tipler.Mesajlar;
import com.cengiz.ilanproject.base.util.TemplateUtils;
import com.cengiz.ilanproject.modules.genel.data.dto.MesajDto;
import com.cengiz.ilanproject.modules.genel.service.MesajService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */


@Data
@Slf4j
public class RestResponse<T extends BaseDto> {

  @JsonProperty("lang")
  private String dilEnum = Dil.TR.getUlkeKodu();

  @JsonProperty("infos")
  private List<RestMesajDTO> infoList = new ArrayList<>();

  @JsonProperty("warnings")
  private List<RestMesajDTO> warningList = new ArrayList<>();

  @JsonProperty("errors")
  private List<RestMesajDTO> errorList = new ArrayList<>();
  @JsonIgnore
  private HttpStatus httpStatus;

  @JsonProperty("data")
  private T data;

  @JsonProperty("listData")
  @JsonInclude(Include.NON_NULL)
  private List<T> listData;

  private boolean success = true;


  public RestResponse<T> createWithMesajEnum(Mesajlar mesajlar) {
    this.addMesaj(mesajlar);
    return this;
  }

  public void addMesaj(Mesajlar mesajlar) {
    MesajService mesajService = BeanUtil.getBean(MesajService.class);
    this.httpStatus = mesajlar.getHttpStatus();
    switch (mesajlar.getSeviye()) {
      case WARN -> this.warningList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
      case ERROR -> {
        this.success = false;
        this.errorList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
      }
      default -> this.infoList.add(new RestMesajDTO(mesajlar.getKodu(), mesajService.getMesajByMesajEnum(mesajlar).getMesaj()));
    }
  }

  public void addMesajWithArgs(Mesajlar mesajlar, Map<String, String> args) {
    MesajService mesajService = BeanUtil.getBean(MesajService.class);
    this.httpStatus = mesajlar.getHttpStatus();
    switch (mesajlar.getSeviye()) {
      case WARN -> this.warningList.add(
          new RestMesajDTO(mesajlar.getKodu(), TemplateUtils.getReturnString(mesajService.getMesajByMesajEnum(mesajlar).getMesaj(), args)));
      case ERROR -> {
        this.success = false;
        this.errorList.add(
            new RestMesajDTO(mesajlar.getKodu(), TemplateUtils.getReturnString(mesajService.getMesajByMesajEnum(mesajlar).getMesaj(), args)));
      }
      default -> this.infoList.add(
          new RestMesajDTO(mesajlar.getKodu(), TemplateUtils.getReturnString(mesajService.getMesajByMesajEnum(mesajlar).getMesaj(), args)));
    }
  }

  public String toJson() {
    ObjectMapper mapper = BeanUtil.getBean(ObjectMapper.class);
    try {
      return mapper.writeValueAsString(this);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return null;
  }

  public RestResponse<T> createOkWithData(T data) {
    RestResponse<T> restResponse = createOk();
    restResponse.data = data;
    return restResponse;
  }

  public RestResponse<T> createOk() {
    this.addMesaj(Mesajlar.GNL_ISLEM_BASARILI);
    return this;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setListData(List<T> data) {
    listData = data;
  }

  public void addMesaj(MesajDto mesaj) {
    Mesajlar mesajlar = Mesajlar.of(mesaj.getAdi());
    this.httpStatus = mesajlar.getHttpStatus();
    switch (mesajlar.getSeviye()) {
      case WARN -> this.warningList.add(new RestMesajDTO(mesaj.getKodu(), mesaj.getMesaj()));
      case ERROR -> {
        this.success = false;
        this.errorList.add(new RestMesajDTO(mesaj.getKodu(), mesaj.getMesaj()));
      }
      default -> this.infoList.add(new RestMesajDTO(mesaj.getKodu(), mesaj.getMesaj()));
    }
  }
}
