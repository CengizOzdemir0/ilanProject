package com.cengiz.ilanproject.modules.ilan.repository;

import com.cengiz.ilanproject.base.repo.BaseJPARepository;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;
import com.cengiz.ilanproject.modules.ilan.data.entity.IlanOnay;

public interface IlanOnayRepository extends BaseJPARepository<IlanOnay, Long> {

  IlanOnay findByFkIlanId(Long ilanId);

}
