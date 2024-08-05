package com.cengiz.ilanproject.config.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import com.cengiz.ilanproject.base.dto.BaseDto;
import com.cengiz.ilanproject.base.tipler.Mesajlar;


/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseHelper {

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromResponse(RestResponse<T> restResponse) {
    return new ResponseEntity<>(restResponse, restResponse.getHttpStatus().getSpringHttpStatus());
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityDataWithMesaj(Mesajlar mesajlarEnum) {
    RestResponse<T> res = new RestResponse<>();
    res.addMesaj(mesajlarEnum);
    return responseEntityFromResponse(res);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityDataWithMesajAndDataList(List<T> data, Mesajlar mesajlarEnum) {
    RestResponse<T> res = new RestResponse<>();
    res.addMesaj(mesajlarEnum);
    res.setListData(data);
    return responseEntityFromResponse(res);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityDataWithMesajAndData(T data, Mesajlar mesajlarEnum) {
    RestResponse<T> res = new RestResponse<>();
    res.addMesaj(mesajlarEnum);
    res.setData(data);
    return responseEntityFromResponse(res);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityDataWithMesaj(Mesajlar mesajlarEnum, Map<String, String> args) {
    RestResponse<T> res = new RestResponse<>();
    res.addMesajWithArgs(mesajlarEnum, args);
    return responseEntityFromResponse(res);
  }

  public static ResponseEntity responseEntityOkWithByteArray(byte[] belge, String belgeAdi) {
    InputStreamResource resource;
    resource = new InputStreamResource(new ByteArrayInputStream(belge));
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + belgeAdi)
        .header("Access-Control-Expose-Headers", "*")
        .header("File-Name", belgeAdi)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .contentLength(belge.length)
        .body(resource);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityOkFromData(T data) {
    return responseEntityFromData(data, HttpStatus.OK, Mesajlar.GNL_ISLEM_BASARILI);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityOkFromListData(List<T> data) {
    return responseEntityFromListData(data, HttpStatus.OK, Mesajlar.GNL_ISLEM_BASARILI);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromData(T data, HttpStatus httpStatus, Mesajlar... mesajList) {
    RestResponse<T> restResponse = new RestResponse<>();
    restResponse.setData(data);
    if (!ObjectUtils.isEmpty(mesajList)) {
      Arrays.stream(mesajList).forEach(restResponse::addMesaj);
    }
    return new ResponseEntity<>(restResponse, httpStatus);
  }

  public static <T extends BaseDto> ResponseEntity<RestResponse<T>> responseEntityFromListData(List<T> data, HttpStatus httpStatus,
      Mesajlar... mesajList) {
    RestResponse<T> restResponse = new RestResponse<>();
    restResponse.setListData(data);
    if (!ObjectUtils.isEmpty(mesajList)) {
      Arrays.stream(mesajList).forEach(restResponse::addMesaj);
    }
    return new ResponseEntity<>(restResponse, httpStatus);
  }

}
