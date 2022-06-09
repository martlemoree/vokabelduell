package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationServiceImpl implements TranslationService {

    private TranslationDao translationDao;

    @Autowired
    public TranslationServiceImpl(TranslationDao translationDao) {
        this.translationDao = translationDao;
    }

    // constructor without parameters is needed for mockito tests
    public TranslationServiceImpl() {}


    public Translation createTranslation(Long translationId, List<String> translations){
        // method not implemented and tested because it is not part of the game logic
        return new Translation(translations) ;}
    public void removeTranslation(Translation translationToBeRemoved){
        // method not implemented and tested because it is not part of the game logic
        translationDao.deleteTranslation(translationToBeRemoved);
    }
}