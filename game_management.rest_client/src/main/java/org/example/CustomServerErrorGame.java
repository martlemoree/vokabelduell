package org.example;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomServerErrorGame extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomServerErrorGame(String message) {
        super(message);
    }
}

