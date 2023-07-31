package com.github.wesleyav.jornadamilhas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "destino")
public class Destino implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 45)
	String foto1;

	@Column(length = 45)
	String foto2;

	@Column(length = 255)
	String nome;

	@Column(length = 160)
	String meta;

	@Column(nullable = true, length = 255)
	String textoDescritivo;

	@Column(length = 45)
	BigDecimal preco;

	public Destino() {
	}

	public Destino(Long id, String foto1, String foto2, String nome, String meta, String textoDescritivo,
			BigDecimal preco) {
		this.id = id;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.nome = nome;
		this.meta = meta;
		this.textoDescritivo = textoDescritivo;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destino other = (Destino) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", foto1=" + foto1 + ", foto2=" + foto2 + ", nome=" + nome + ", meta=" + meta
				+ ", textoDescritivo=" + textoDescritivo + ", preco=" + preco + "]";
	}

}
