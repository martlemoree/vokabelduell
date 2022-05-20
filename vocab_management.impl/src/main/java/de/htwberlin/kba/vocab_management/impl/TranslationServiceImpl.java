package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {

    //Todo würde ich wieder rausnehmen weil die translations nur über createVocablist angelegt werden
    public Translation createTranslation(Long translationId, List<String> translations){ return null ;}
    public void removeTranslation(Translation translation,String translationToBeRemoved){}
    public void addTranslation(Translation translation,String newTranslation){}
}