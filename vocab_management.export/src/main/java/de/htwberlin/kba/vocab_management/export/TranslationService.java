package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public interface TranslationService {

    /**
     * method offers functionality to create new translation from user input according to given standard
     * @param translations string list of synonymous translations. can also contain just one entry if no synonym given
     * @return Translation that is created
     */
    Translation createTranslation(List<String> translations);

    /**
     * removes a specific translation from the String list of translations.
     * @param translationId the object that should be changed
     * */
    void removeTranslation(Long translationId);

    /**
     * gives back list of strings with translations
     * @param translationId id of translation
     * @return string list with translations
     */
    List<String> getAllTranslationStrings(Long translationId);

    /**
     * method to get all existing translations from database
     * @return list of all translations
     */
    List<Translation> getAllTranslations();

}
