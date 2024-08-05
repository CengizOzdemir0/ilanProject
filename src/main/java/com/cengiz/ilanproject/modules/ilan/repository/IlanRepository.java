package com.cengiz.ilanproject.modules.ilan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.cengiz.ilanproject.base.repo.BaseJPARepository;
import com.cengiz.ilanproject.modules.ilan.data.entity.Ilan;

public interface IlanRepository extends BaseJPARepository<Ilan, Long> {


  List<Ilan> findByAktifFalse();


  @Query("""
      FROM Ilan i JOIN IlanOnay io on i.id = io.fkIlanId
      where io.onayDurumu = 2 order by i.kayitZamani desc limit 10 """)
  List<Ilan> onaylanmisSonİlanlar();


}
