package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;

import java.util.List;

public interface TranslationDao {

    void createTranslation(Translation translation);

    Translation getTranslationById(Long translationId);

    void updateTranslation(Translation translation);

    List<Translation> getAllTranslations();

    void deleteTranslation(Long translationId);

}
