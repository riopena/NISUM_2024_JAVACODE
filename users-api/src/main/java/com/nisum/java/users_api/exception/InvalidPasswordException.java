package com.nisum.java.users_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException {
	
	public InvalidPasswordException(String message) {
		super(message);
	}

}
