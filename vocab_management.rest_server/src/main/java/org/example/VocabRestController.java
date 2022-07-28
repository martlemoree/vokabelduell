package org.example;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/vocab")
public class VocabRestController {

    public VocabService vocabService;

    @Autowired
    public VocabRestController(VocabService vocabService) {
        this.vocabService = vocabService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createVocab(@RequestBody Vocab vocab) throws URISyntaxException {
        Vocab newVocab = vocabService.createVocab(vocab.getVocabs(),vocab.getTranslations());
        URI uri = new URI("/vocab/" + newVocab.getVocabId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/edit/{id}") // funktioniert nicht, weil Vocab eine Liste ist und das kann
    public ResponseEntity<Vocab> editVocabs(@PathVariable("vocabString") String vocabString, @RequestBody List<String> newVocabList) throws VocabListObjectNotFoundException {
        Vocab vocab = vocabService.getVocabByVocabString(vocabString);
        vocab.setVocabs(newVocabList);
        return ResponseEntity.ok(vocab);
    }

    @PutMapping(value = "/editTranslations/{vocabString}")
    public ResponseEntity<Vocab> editTranslations(@PathVariable("vocabString") String vocabString, @RequestBody List<Translation> newTranslationList) throws VocabListObjectNotFoundException {
        Vocab vocab = vocabService.getVocabByVocabString(vocabString);
        vocab.setTranslations(newTranslationList);
        return ResponseEntity.ok(vocab);
    }

    @GetMapping(value = "/{vocabString}") // 404 Not Found
    public Vocab getVocabByVocabString(@PathVariable("vocabString") String vocabString) throws VocabListObjectNotFoundException {
        Vocab vocab = vocabService.getVocabByVocabString(vocabString);
        return vocab;
    }
}