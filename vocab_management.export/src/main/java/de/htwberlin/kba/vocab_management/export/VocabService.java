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

    // TODO DAO

    /**
     * holds logic to identify and get a vocab by given name
     * @param vocabName supposed name of a vocab
     * @return vocab with given name
     */
    Vocab getVocabByName(String vocabName);

    /**
     * holds logic to give a random entry from the list of strings of the given vocab
     * @param vocab given vocab
     * @return random string entry from list of strings of vocab
     */
    String giveVocabStringRandom(Vocab vocab);
}
