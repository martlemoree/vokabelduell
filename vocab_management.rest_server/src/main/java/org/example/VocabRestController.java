package org.example;

import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vocab")
public class VocabRestController {

    public VocabService vocabService;

    @Autowired
    public VocabRestController(VocabService vocabService) {
        this.vocabService = vocabService;
    }

    /*
    @GetMapping(value = "/all")
    public List<Vocab> getVocabs
   brauchen wir den überhaupt?
     */
}
