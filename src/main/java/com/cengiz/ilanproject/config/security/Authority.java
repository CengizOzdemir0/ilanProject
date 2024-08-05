package com.cengiz.ilanproject.config.security;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
  private final String auth;

  @Override
  public String getAuthority() {
    return auth;
  }
}
