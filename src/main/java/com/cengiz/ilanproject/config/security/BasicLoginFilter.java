package com.cengiz.ilanproject.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cengiz.ilanproject.modules.kys.data.dto.KullaniciDto;
import com.cengiz.ilanproject.modules.kys.service.KullaniciService;

@Component
@Slf4j
@RequiredArgsConstructor
public class BasicLoginFilter extends OncePerRequestFilter{
  private final KullaniciService kullaniciService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String userName = request.getHeader("user-name");
    String password = request.getHeader("password");


    if (Objects.nonNull(userName) && Objects.nonNull(password)) {
      KullaniciDto bytelefon = kullaniciService.getBytelefon(Long.valueOf(userName));
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(bytelefon, bytelefon.getId(),getYetkiList(bytelefon.getKullaniciTipi()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request,response);
  }

  private Collection<? extends GrantedAuthority> getYetkiList(Integer kullaniciTipi) {
    // YONETİCİ kullanıcı tipi kullanıcı tablosunda 1, onu kontrol ediyorum yetki için, 2  de kullanıcı
    List<Authority> list = new ArrayList<>();
    if (kullaniciTipi.equals(1)){
      list.add(new Authority("YONETICI"));
    } else if (kullaniciTipi.equals(2)) {
      list.add(new Authority("KULLANICI"));
    }
    return list;
  }
}
