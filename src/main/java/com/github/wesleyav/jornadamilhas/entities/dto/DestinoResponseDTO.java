package com.github.wesleyav.jornadamilhas.entities.dto;

import com.github.wesleyav.jornadamilhas.entities.Destino;

public class DestinoResponseDTO {

	String foto1;

	String foto2;

	String nome;

	String meta;

	String textoDescritivo;

	public DestinoResponseDTO(Destino destino) {
		this.foto1 = destino.getFoto1();
		this.foto2 = destino.getFoto2();
		this.nome = destino.getNome();
		this.meta = destino.getMeta();
		this.textoDescritivo = destino.getTextoDescritivo();
	}

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getTextoDescritivo() {
		return textoDescritivo;
	}

	public void setTextoDescritivo(String textoDescritivo) {
		this.textoDescritivo = textoDescritivo;
	}

}
