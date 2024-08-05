package com.cengiz.ilanproject.modules.ilan.service;


import com.cengiz.ilanproject.modules.ilan.data.dto.RaporDto;

public interface RaporService {

  RaporDto save(RaporDto raporDto);

  RaporDto raporGoruntume(Long ilanId);
}
