package com.github.wesleyav.jornadamilhas.services.exceptions;

public class DestinoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DestinoNotFoundException(Object id) {
		super("Nenhum destino foi encontrado");
	}

}
