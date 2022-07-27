package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.CustomOptimisticLockExceptionVocab;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;

import java.util.List;

public interface TranslationDao {

    void createTranslation(Translation translation);

    Translation getTranslationById(Long translationId) throws VocabListObjectNotFoundException;

    void updateTranslation(Translation translation) throws CustomOptimisticLockExceptionVocab;

    List<Translation> getAllTranslations();

    void deleteTranslation(Long translationId);

}
