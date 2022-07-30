package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import de.htwberlin.kba.vocab_management.export.Vocab;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public Translation createTranslation(List<String> translations) {
        Translation translation = new Translation(translations);
        translationDao.createTranslation(translation);
        Hibernate.initialize(translation.getTranslations());
        return translation;
    }

    @Transactional
    public void removeTranslation(Long translationId) {
        // method not implemented and tested because it is not part of the game logic
        translationDao.deleteTranslation(translationId);
    }

    @Transactional
    public List<String> getAllTranslationStrings(Long translationId){
        List<Translation> translations = translationDao.getTranslationStrings(translationId);
        for (Translation t: translations){
            Hibernate.initialize(t.getTranslations());
            Hibernate.initialize(t.getVocabs());        }
        List<String> translationStrings = translations.get(0).getTranslations();
        return translationStrings;
    }

    @Transactional
    public List<Translation> getAllTranslations() {
        return translationDao.getAllTranslations();
    }
}
