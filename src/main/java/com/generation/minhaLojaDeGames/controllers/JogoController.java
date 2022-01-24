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

import com.generation.minhaLojaDeGames.models.Jogo;
import com.generation.minhaLojaDeGames.repositories.JogoRepository;

@RestController
@RequestMapping("/jogos")
@CrossOrigin("*")

public class JogoController {

	@Autowired
	private JogoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{nome_jogo}")
	public ResponseEntity<List<Jogo>> getByName(@PathVariable String nome_jogo){
		List<Jogo> nome = repository.findAllByJogoContainingIgnoreCase(nome_jogo);
		
		if(nome.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(nome);
		}
	}
	
	@PostMapping("/post")
	public ResponseEntity<Jogo> post(@RequestBody Jogo jogo){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogo));
	}
	
	@PutMapping("/put")
	public ResponseEntity<Jogo> put(@RequestBody Jogo jogo){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(jogo));
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}
}


