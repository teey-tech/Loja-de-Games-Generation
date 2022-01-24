package com.generation.minhaLojaDeGames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.minhaLojaDeGames.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


	public List<Categoria> findAllByNomeContainingIgnoreCase (String nome);

	public List<Categoria> findAll();
}
