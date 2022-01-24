package com.generation.minhaLojaDeGames.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.minhaLojaDeGames.models.Categoria;
import com.generation.minhaLojaDeGames.repositories.CategoriaRepository;

@RestController
@RequestMapping ("/categorias")
@CrossOrigin ("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Categoria>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/titulo/{nome_categoria}")
	public ResponseEntity <List<Categoria>> getByName(@PathVariable String nome_categoria){
		List <Categoria> nome = repository.findAllByTipoCategoriaContainingIgnoreCase(nome_categoria);
		
		if (nome.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(nome);
		}
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Categoria> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping ("/post")
	public ResponseEntity <Categoria> post(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}

	@PutMapping ("/put")
	public ResponseEntity <Categoria> put(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping ("/delete/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
