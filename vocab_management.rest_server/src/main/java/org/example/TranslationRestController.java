package org.example;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translation")
public class TranslationRestController {

    public TranslationService translationService;

    @Autowired
    public TranslationRestController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping(value = "/create")
    public String createTranslation(@RequestBody Translation translation) {
        translationService.createTranslation(translation.getTranslations());
        return "Translation was successfully created.";
    }

    @DeleteMapping(value = "/delete/{translationId}")
    public String removeTranslation(@PathVariable("translationId") Long translationId) {
        translationService.removeTranslation(translationId);
        return "Translation was successfully deleted.";
    }
}
