package com.generation.minhaLojaDeGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import com.generation.minhaLojaDeGames.models.Usuario;
import com.generation.minhaLojaDeGames.models.UsuarioLogin;
import com.generation.minhaLojaDeGames.repositories.UsuarioRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository repository;

  public Usuario CadastrarUsuario(Usuario usuario) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String senhaEncoder = encoder.encode(usuario.getSenha());
    usuario.setSenha(senhaEncoder);

    return repository.save(usuario);
  }

  public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
    if (usuario.isPresent()) {
      if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
        String auth = user.get().getEmail() + ":" + user.get().getSenha();
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodeAuth);

        user.get().setToken(authHeader);
        user.get().setNome(usuario.get().getNome());

        return user;
      }
    }

    return null;
  }
}
