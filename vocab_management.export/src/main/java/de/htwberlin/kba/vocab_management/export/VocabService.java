package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public interface VocabService {

    /**
     * a new German Vocabulary is created.
     * @param vocabId is the unqiue identifier of a vocabulary
     * @param vocabs is a list with the vocabulary and its different synonyms.
     * @param translations is a list of the different translation of the vocabulary. A vocabulary can have different meanings so that
     *                     there are several translations for every different meaning.
     * @return a new vocabulary
     */
    Vocab createVocab(Long vocabId, List<String> vocabs, List<Translation> translations);

    /**
     * change the items of a vocabulary
     * @param vocab the object that should be changed
     * @param newVocabs list with the new vocabularies of this object
     */
    void editVocabs(Vocab vocab, List<String> newVocabs);

    /**
     * change the translation of a vocabulary
     * @param vocab the object that should be changed
     * @param translations the list with new translations of a vocabulary
     */
    void editTranslations(Vocab vocab, List<Translation> translations);

    /**
     * holds logic to identify and get a vocab by given string
     * @param vocabString given String
     * @return vocab with given name
     */
    Vocab getVocabByVocabString(String vocabString);
}
