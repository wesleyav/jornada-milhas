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

import com.github.wesleyav.jornadamilhas.entities.Depoimento;
import com.github.wesleyav.jornadamilhas.services.DepoimentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Depoimento")
public class DepoimentoController {

	private DepoimentoService depoimentoService;

	public DepoimentoController(DepoimentoService depoimentoService) {
		this.depoimentoService = depoimentoService;
	}

	@GetMapping(value = "/depoimentos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar de forma paginada todos os depoimentos")
	public ResponseEntity<Page<Depoimento>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Depoimento> depoimentoPage = depoimentoService.findAll(pageNumber, pageSize);

		return new ResponseEntity<>(depoimentoPage, HttpStatus.OK);
	}

	@GetMapping(value = "/depoimentos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para listar um depoimento por id")
	public ResponseEntity<Depoimento> findById(@PathVariable Long id) {
		Depoimento depoimento = depoimentoService.findById(id);
		return new ResponseEntity<>(depoimento, HttpStatus.OK);
	}

	@PostMapping(value = "/depoimentos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para criar um depoimento")
	public ResponseEntity<Depoimento> save(@RequestBody Depoimento obj) {
		Depoimento depoimento = depoimentoService.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/depoimentos/{id}")
				.buildAndExpand(depoimento.getId()).toUri();
		return ResponseEntity.created(uri).body(depoimento);
	}

	@DeleteMapping(value = "/depoimentos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para deletar um depoimento")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		depoimentoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/depoimentos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para atualizar um depoimento")
	public ResponseEntity<Depoimento> update(@PathVariable Long id, @RequestBody Depoimento obj) {
		Depoimento depoimento = depoimentoService.update(id, obj);
		return new ResponseEntity<>(depoimento, HttpStatus.OK);
	}

	@GetMapping(value = "/depoimentos-home", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint para exibir de forma randômica os depoimentos de 3 pessoas")
	public ResponseEntity<List<Depoimento>> getDepoimentosHome() {
		List<Depoimento> depoimentos = depoimentoService.getDepoimentosAleatorios(3);
		return new ResponseEntity<>(depoimentos, HttpStatus.OK);
	}

}
