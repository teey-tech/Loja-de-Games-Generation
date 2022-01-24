package com.generation.minhaLojaDeGames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.minhaLojaDeGames.models.Jogo;


@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	public List<Jogo> findAllByJogoContainingIgnoreCase (String jogo);
	
	public List<Jogo> findAll();
}
