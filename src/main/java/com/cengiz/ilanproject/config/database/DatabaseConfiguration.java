package com.cengiz.ilanproject.config.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Cengiz ÖZDEMİR
 * @created 03/08/2024 - 22:08
 */

@Configuration
public class DatabaseConfiguration {

  @Value("${spring.application.name:APP_NAME}")
  String applicationName;

  @Value("${spring.datasource.hikari.connection-timeout}")
  Long connectionTimeout;

  @Value("${spring.datasource.hikari.minimum-idle}")
  Integer minimumIdle;

  @Value("${spring.datasource.hikari.maximum-pool-size}")
  Integer maximumPoolSize;

  @Value("${spring.datasource.hikari.idle-timeout}")
  Long idleTimeout;

  @Value("${spring.datasource.hikari.max-lifetime}")
  Long maxLifetime;

  @Primary
  @Bean(name = "dataSource")
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "namedJdbc")
  @DependsOn("dataSource")
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
    ((HikariDataSource) dataSource).setConnectionTimeout(connectionTimeout);
    ((HikariDataSource) dataSource).setMinimumIdle(minimumIdle);
    ((HikariDataSource) dataSource).setMaximumPoolSize(maximumPoolSize);
    ((HikariDataSource) dataSource).setIdleTimeout(idleTimeout);
    ((HikariDataSource) dataSource).setMaxLifetime(maxLifetime);
    return new NamedParameterJdbcTemplate(dataSource);
  }

}
