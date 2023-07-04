package com.github.wesleyav.jornadamilhas.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.wesleyav.jornadamilhas.entities.Depoimento;
import com.github.wesleyav.jornadamilhas.repositories.DepoimentoRepository;
import com.github.wesleyav.jornadamilhas.services.exceptions.ResourceEmptyException;
import com.github.wesleyav.jornadamilhas.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepoimentoService {

	private DepoimentoRepository depoimentoRepository;

	public DepoimentoService(DepoimentoRepository depoimentoRepository) {
		this.depoimentoRepository = depoimentoRepository;
	}

	@Transactional
	public Depoimento save(Depoimento depoimento) {
		return depoimentoRepository.save(depoimento);
	}

	@Transactional
	public Depoimento findById(Long id) {
		return depoimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Page<Depoimento> findAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Depoimento> depoimentoPage = depoimentoRepository.findAll(pageable);

		if (depoimentoPage.isEmpty()) {
			throw new ResourceEmptyException("Não foram encontrados registros, base vazia.");
		}

		return depoimentoPage;
	}

	@Transactional
	public void deleteById(Long id) {
		depoimentoRepository.deleteById(id);
	}

	@Transactional
	public Depoimento update(Long id, Depoimento obj) {

		try {
			Depoimento depoimento = depoimentoRepository.getReferenceById(id);
			depoimento.setImageUrl(obj.getImageUrl());
			depoimento.setDepoimento(obj.getDepoimento());
			depoimento.setDepoente(obj.getDepoente());

			return depoimentoRepository.save(depoimento);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}
