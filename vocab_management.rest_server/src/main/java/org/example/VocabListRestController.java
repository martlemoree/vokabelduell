package org.example;

import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocablist")
public class VocabListRestController {

    public VocabListService vocabListService;

    @Autowired
    public VocabListRestController(VocabListService vocabListService) {
        this.vocabListService = vocabListService;
    }

    /*
    @PostMapping(value = "/create")
    public String createVocabList(@RequestBody VocabList vocabList) {
        vocabListService.createVocabList(vocabList.)
    }
     */

    @DeleteMapping(value = "/delete/{vocabListId}")
    public String removeVocabList(@PathVariable("vocabListId") Long vocabListId) throws VocabListObjectNotFoundException {
        VocabList vocabList = vocabListService.getVocabListById(vocabListId);
        vocabListService.removeVocabList(vocabList);
        return "Vocab List was successfully deleted.";
    }

    @GetMapping(value = "/randomlists/{gameId}")
    public ResponseEntity<Long> getRandomVocabLists(@PathVariable("gameId") Long gameId) {
        List<Long> vocabListLists = vocabListService.getRandomVocabLists();

        HttpHeaders headers = new HttpHeaders();
        headers.add("vocabListLists1", String.valueOf(vocabListLists.get(0)));
        headers.add("vocabListLists2", String.valueOf(vocabListLists.get(1)));
        headers.add("vocabListLists3", String.valueOf(vocabListLists.get(1)));

        return ResponseEntity.ok().headers(headers).body(gameId);
    }

    @GetMapping(value = "/all")
    public List<VocabList> getVocabLists() {
        List<VocabList> vocabListList = vocabListService.getVocabLists();
        return vocabListList;
    }

    @PutMapping(value = "/edit/{vocabListId}/{name}")
    public String editName(@PathVariable("vocabListId") Long vocabListId,
                           @PathVariable("name") String name) throws VocabListObjectNotFoundException {
        VocabList vocabList = vocabListService.getVocabListById(vocabListId);
        vocabListService.editName(vocabList, name);
        return "Name of Vocab List was successfully changed.";
    }

    @PutMapping(value = "/edit/{vocabListId}/{language}")
    public String editLanguage(@PathVariable("vocabListId") Long vocabListId,
                               @PathVariable("language") String language) throws VocabListObjectNotFoundException {
        VocabList vocabList = vocabListService.getVocabListById(vocabListId);
        vocabListService.editLanguage(vocabList, language);
        return "Language of Vocab List was successfully changed.";
    }

    @PutMapping(value = "/edit/{vocabListId}/{category}")
    public String editCategory(@PathVariable("vocabListId") Long vocabListId,
                               @PathVariable("category") String category) throws VocabListObjectNotFoundException {
        VocabList vocabList = vocabListService.getVocabListById(vocabListId);
        vocabListService.editName(vocabList, category);
        return "Category of Vocab List was successfully changed.";
    }
}
