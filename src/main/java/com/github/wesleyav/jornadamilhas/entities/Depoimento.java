package com.github.wesleyav.jornadamilhas.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "depoimento")
public class Depoimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 45)
	String imageUrl;

	@Column(length = 255)
	String depoimento;

	@Column(length = 45)
	String depoente;

	public Depoimento() {
	}

	public Depoimento(Long id, String imageUrl, String depoimento, String depoente) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.depoimento = depoimento;
		this.depoente = depoente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDepoimento() {
		return depoimento;
	}

	public void setDepoimento(String depoimento) {
		this.depoimento = depoimento;
	}

	public String getDepoente() {
		return depoente;
	}

	public void setDepoente(String depoente) {
		this.depoente = depoente;
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
		Depoimento other = (Depoimento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Depoimento [id=" + id + ", imageUrl=" + imageUrl + ", depoimento=" + depoimento + ", depoente="
				+ depoente + "]";
	}

}
