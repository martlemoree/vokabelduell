package org.example;

import de.htwberlin.kba.configuration.RestTemplateResponseErrorHandler;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VocabRestAdapter implements VocabService {

    private RestTemplate restTemplate;

    final String localhostVocab = "http://localhost:8080/vocab/";

    @Autowired
    public VocabRestAdapter(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Vocab createVocab(List<String> vocabs, List<Translation> translations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostVocab + "create";
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Vocab.class).getBody();
    }

    public void editVocabs(Vocab vocab, List<String> newVocabs) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        String vocabString = null;//FIXME
        final String URL = localhostVocab + "edit/" + vocabString;
        restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, void.class).getBody();
    }

    public void editTranslations(Vocab vocab, List<Translation> translations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        List<String> vocabStringList = vocab.getVocabs();
        String vocabString = null;//FIXME
        final String URL = localhostVocab + "editTranslations/" + vocabString;
        restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, void.class).getBody();
    }

    public Vocab getVocabByVocabString(String vocabString) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostVocab + vocabString;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Vocab.class).getBody();
    }

    public Vocab getVocabByVocabId(Long vocabId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        final String URL = localhostVocab + vocabId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Vocab.class).getBody();
    }
}