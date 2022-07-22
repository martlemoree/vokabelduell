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

    @Transactional
    public Translation createTranslation(Long translationId, List<String> translations) {
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
}
