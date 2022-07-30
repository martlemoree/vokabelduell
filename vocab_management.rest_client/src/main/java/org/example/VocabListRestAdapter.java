package org.example;

import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VocabListRestAdapter implements VocabListService {

    private RestTemplate restTemplate;

    final String localhostVocablist = "http://localhost:8080/vocablist/";

    @Autowired
    public VocabListRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

    public VocabList createVocabList(String text) throws FileNotFoundException {
        return null;
    }

    public void editName(VocabList vocabList, String newName) {

    }

    public void editLanguage(VocabList vocabList, String newLanguage) {

    }

    public void editCategory(VocabList vocabList, String newCat) {

    }

    public void removeVocab(VocabList vocabList, Vocab vocab) {

    }

    public List<Long> getRandomVocabLists() {
        return null;
    }

    public void removeVocabList(VocabList vocabList) {

    }

    public String readFile(String path) throws FileNotFoundException {
        return null;
    }

    public List<VocabList> getVocabLists() {
        final String URL = localhostVocablist + "all";
        return restTemplate.exchange(URL, HttpMethod.GET, null, List.class).getBody();    }

    public VocabList getVocabListByName(String vocabListName) {
        return null;
    }

    public VocabList getVocabListById(Long id) {
        return null;
    }
}