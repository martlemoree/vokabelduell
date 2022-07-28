package org.example;

import de.htwberlin.kba.configuration.RestTemplateResponseErrorHandler;
import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TranslationRestAdapter {

    private RestTemplate restTemplate;

    final String localhostTranslation = "http://localhost:8080/translation/";

    @Autowired
    public TranslationRestAdapter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate =  restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Translation createTranslation(List<String> translations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostTranslation + "create";
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Translation.class).getBody();
    }

    public boolean removeTranslation(Long translationId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostTranslation + "delete/" + translationId;
        HttpEntity<Long> httpEntity = new HttpEntity<>(translationId);
        return restTemplate.exchange(URL, HttpMethod.DELETE, httpEntity, boolean.class).getBody();
    }

}
