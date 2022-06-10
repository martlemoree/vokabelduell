package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public interface TranslationService {

    /**
     * method offers functionality to create new translation from user input according to given standard
     * @param translationId necessary parameter for new translation object, must be unique
     * @param translations string list of synonymous translations. can also contain just one entry if no synonym given
     * @return Translation object
     */
    Translation createTranslation(Long translationId, List<String> translations);

    /**
     * removes a specific translation from the String list of translations.
     * @param translation the object that should be changed
     * */
    void removeTranslation(Translation translation);

}
