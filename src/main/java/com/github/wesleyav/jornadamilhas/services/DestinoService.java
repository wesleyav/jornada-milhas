package com.github.wesleyav.jornadamilhas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.wesleyav.jornadamilhas.entities.Destino;
import com.github.wesleyav.jornadamilhas.repositories.DestinoRepository;
import com.github.wesleyav.jornadamilhas.services.exceptions.DestinoNotFoundException;
import com.github.wesleyav.jornadamilhas.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.jornadamilhas.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DestinoService {

	private DestinoRepository destinoRepository;

	public DestinoService(DestinoRepository destinoRepository) {
		this.destinoRepository = destinoRepository;
	}

	@Transactional
	public Destino save(Destino destino) {
		return destinoRepository.save(destino);
	}

	@Transactional
	public Destino findById(Long id) {
		return destinoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Page<Destino> findAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Destino> destinoPage = destinoRepository.findAll(pageable);

		if (destinoPage.isEmpty()) {
			throw new ResourceEmptyException("Não foram encontrados registros, base vazia.");
		}

		return destinoPage;
	}

	@Transactional
	public List<Destino> findByNomeContainingIgnoreCase(String nome) {
		List<Destino> destinos = destinoRepository.findByNomeContainingIgnoreCase(nome);
		if (destinos.isEmpty()) {
			throw new DestinoNotFoundException(nome);
		}
		return destinos;
	}

	@Transactional
	public void deleteById(Long id) {
		destinoRepository.deleteById(id);
	}

	@Transactional
	public Destino update(Long id, Destino obj) {

		try {
			Destino destino = destinoRepository.getReferenceById(id);
			destino.setFoto1(obj.getFoto1());
			destino.setFoto2(obj.getFoto2());
			destino.setNome(obj.getNome());
			destino.setMeta(obj.getMeta());
			destino.setTextoDescritivo(obj.getTextoDescritivo());
			destino.setPreco(obj.getPreco());
			return destinoRepository.save(destino);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}
