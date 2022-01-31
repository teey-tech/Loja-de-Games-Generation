package com.generation.minhaLojaDeGames.seguranca;

import java.util.Optional;

import com.generation.minhaLojaDeGames.models.Usuario;
import com.generation.minhaLojaDeGames.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
 * @Author Igor Miramisawa
 * 
 */


@Service
public class UserDetailsServiceImplements implements UserDetailsService {

  @Autowired
  private UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Usuario> user = userRepository.findByEmail(email);
    user.orElseThrow(() -> new UsernameNotFoundException(email + "Não existe."));

    return user.map(UserDetailsImplements::new).get();
  }
}
