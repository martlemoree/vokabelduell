package org.example;

import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TranslationRestAdapter {

    private static final String localhost = "http://localhost:8080/";

    private RestTemplate restTemplate;

    @Autowired
    public TranslationRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
    public ResponseEntity<String> createTranslation(List<String> translations) {
        HttpEntity<Translation> request = new HttpEntity<>(new Translation(translations));


    }*/
}
