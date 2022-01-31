package com.generation.minhaLojaDeGames.seguranca;

import java.util.Collection;


/*
 * @Author Igor Miramisawa
 * 
 */

import com.generation.minhaLojaDeGames.models.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplements implements UserDetails {

  private static final long serialVersionUID = 1l;

  private String email;
  private String password;

  public UserDetailsImplements(Usuario user) {
    this.email = user.getEmail();
    this.password = user.getSenha();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
