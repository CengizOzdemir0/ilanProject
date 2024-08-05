package com.cengiz.ilanproject.modules.ilan.service.impl;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.cengiz.ilanproject.modules.ilan.data.mapper.IlanOnayMapper;
import com.cengiz.ilanproject.modules.ilan.repository.IlanOnayRepository;
import com.cengiz.ilanproject.modules.ilan.service.IlanOnayService;

@Service
@RequiredArgsConstructor
public class IlanOnayServiceImpl implements IlanOnayService {

  private final IlanOnayRepository ilanOnayRepository;
  private final IlanOnayMapper ilanOnayMapper;



}
