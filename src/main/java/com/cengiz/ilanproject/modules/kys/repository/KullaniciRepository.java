package com.cengiz.ilanproject.modules.kys.repository;






import com.cengiz.ilanproject.base.repo.BaseJPARepository;
import com.cengiz.ilanproject.modules.kys.data.entity.Kullanici;

/**
 * @author Cengiz ÖZDEMİR
 * @created 09/03/2024 - 22:08
 */

public interface KullaniciRepository extends BaseJPARepository<Kullanici, Long> {

  boolean existsByTelefon(Long telefon);
  Kullanici findByEposta(String eposta);

  Kullanici findByTelefon(Long telefon);

}
