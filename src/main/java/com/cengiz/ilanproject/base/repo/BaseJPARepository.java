package com.cengiz.ilanproject.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@NoRepositoryBean
public interface BaseJPARepository<T, I> extends JpaRepository<T, I> {

}
