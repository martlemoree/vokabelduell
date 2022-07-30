package org.example;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getStrings/{translationId}")
    public List<String> getAllTranslationStrings(@PathVariable("translationId") Long translationId) {
        return translationService.getAllTranslationStrings(translationId);
    }

    @GetMapping(value = "/all")
    public List<Translation> getTranslations() {
        List<Translation> translations = translationService.getAllTranslations();
        return translations;
    }
}
