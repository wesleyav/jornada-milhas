package com.github.wesleyav.jornadamilhas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.wesleyav.jornadamilhas.entities.Destino;
import com.github.wesleyav.jornadamilhas.services.DestinoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Destino")
public class DestinoController {

	private DestinoService destinoService;

	public DestinoController(DestinoService destinoService) {
		this.destinoService = destinoService;
	}

	@GetMapping(value = "/destinos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar de forma paginada todos os destinos")
	public ResponseEntity<Page<Destino>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Destino> destinoPage = destinoService.findAll(pageNumber, pageSize);

		return new ResponseEntity<>(destinoPage, HttpStatus.OK);
	}

	@GetMapping(value = "/destinos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar um destinos por id")
	public ResponseEntity<Destino> findById(@PathVariable Long id) {
		Destino destino = destinoService.findById(id);
		return new ResponseEntity<>(destino, HttpStatus.OK);
	}

	@GetMapping(value = "/destinos-nome{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para buscar um destinos por nome")
	public ResponseEntity<List<Destino>> findByNomeContainingIgnoreCase(@RequestParam String nome) {
		List<Destino> destinos = destinoService.findByNomeContainingIgnoreCase(nome);
		return new ResponseEntity<>(destinos, HttpStatus.OK);
	}

	@PostMapping(value = "/destinos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um destino")
	public ResponseEntity<Destino> save(@RequestBody Destino obj) {
		Destino destino = destinoService.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/destinos/{id}")
				.buildAndExpand(destino.getId()).toUri();
		return ResponseEntity.created(uri).body(destino);
	}

	@DeleteMapping(value = "/destinos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para deletar um destino")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		destinoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/destinos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um destino")
	public ResponseEntity<Destino> update(@PathVariable Long id, @RequestBody Destino obj) {
		Destino destino = destinoService.update(id, obj);
		return new ResponseEntity<>(destino, HttpStatus.OK);
	}

}
