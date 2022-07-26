package org.example;

import de.htwberlin.kba.configuration.RestTemplateResponseErrorHandler;
import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public String createTranslation(Translation translation) {
        final String URL = localhostTranslation + "create";
        HttpEntity<Translation> httpEntity = new HttpEntity<>(translation);
        return restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class).getBody();
    }

    public String removeTranslation(Long translationId) {
        final String URL = localhostTranslation + "delete/" + translationId;
        HttpEntity<Long> httpEntity = new HttpEntity<>(translationId);
        return restTemplate.exchange(URL, HttpMethod.DELETE, httpEntity, String.class).getBody();
    }

}
