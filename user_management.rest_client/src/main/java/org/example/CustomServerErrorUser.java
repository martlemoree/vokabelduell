package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomServerErrorUser extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomServerErrorUser(String message) {
        super(message);
    }
}