package com.github.wesleyav.jornadamilhas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wesleyav.jornadamilhas.entities.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

	List<Destino> findByNomeContainingIgnoreCase(String nome);

}
