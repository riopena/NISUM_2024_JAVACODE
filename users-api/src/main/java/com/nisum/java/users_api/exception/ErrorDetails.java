package com.nisum.java.users_api.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String mensaje;

	public ErrorDetails(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}
