package de.htwberlin.kba.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomServerError extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomServerError(String message) {
        super(message);
    }
}
