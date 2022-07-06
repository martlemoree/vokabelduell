package org.example;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TranslationRestController {

    private final TranslationService translationService;

    @Autowired
    public TranslationRestController(TranslationService translationService) {
        this.translationService = translationService;
    }

    /*
    @PostMapping (path = "/translations")
    public ResponseEntity<List<Translation>> getTranslations() {
        var translation translationService.createTranslation()
    }
     */
}
