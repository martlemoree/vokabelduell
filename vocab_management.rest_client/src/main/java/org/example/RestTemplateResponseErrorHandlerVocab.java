package org.example;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandlerVocab implements ResponseErrorHandler {

//    https://www.baeldung.com/spring-rest-template-error-handling

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
            throw new CustomServerErrorVocab("Server Error: "+clientHttpResponse.getBody());
        } else if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new CustomServerErrorVocab("Client Error: "+clientHttpResponse.getBody());
        } else if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new CustomServerErrorVocab("Unknown Error: "+clientHttpResponse.getBody());
        }

    }
}