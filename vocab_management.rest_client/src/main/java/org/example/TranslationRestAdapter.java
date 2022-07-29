package org.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;

@Service
public class TranslationRestAdapter implements TranslationService {

    private RestTemplate restTemplate;

    final String localhostTranslation = "http://localhost:8080/translation/";

    @Autowired
    public TranslationRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

    // das müssen wir so lassen da hier eine liste übergeben werden muss
    public Translation createTranslation(List<String> translations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostTranslation + "create";
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Translation.class).getBody();
    }


    public void removeTranslation(Long translationId) {
        final String URL = localhostTranslation + "delete/" + translationId;
        HttpEntity<Long> httpEntity = new HttpEntity<>(translationId);
        restTemplate.exchange(URL, HttpMethod.DELETE, httpEntity, String.class).getBody();
    }

}
