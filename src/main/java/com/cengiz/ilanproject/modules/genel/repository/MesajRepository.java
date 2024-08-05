package com.cengiz.ilanproject.modules.genel.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cengiz.ilanproject.base.repo.BaseJPARepository;
import com.cengiz.ilanproject.modules.genel.data.entity.Mesaj;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

public interface MesajRepository extends BaseJPARepository<Mesaj, Long> {

  @Query(value = """ 
      SELECT * FROM genel.mesajlar m where m.kodu = :kodu or m.adi = :adi """, nativeQuery = true)
  Mesaj findByKoduAndAdi(@Param("kodu") String kodu, @Param("adi") String adi);

  Mesaj findByAdi(String adi);

  void deleteByIdAndKodu(Long id, String kodu);

  boolean existsByKoduAndAdi(String kodu, String adi);


}
