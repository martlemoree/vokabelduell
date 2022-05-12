package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public interface VocabService {

    /**
     * a new German Vocabulary is created.
     * @param vocabId is the unqiue identifier of a vocabulary
     * @param vocabs is a list with the vocabulary and its different synonyms.
     * @param vocablist is the vocablist of the vocabulary
     * @param translations is a list of the different translation of the vocabulary. A vocabulary can have different meanings so that
     *                     there are several translations for every different meaning.
     * @return a new vocabulary
     */
    public Vocab createVocab(Long vocabId, List<String> vocabs, VocabList vocablist, List<Translation> translations);

    /**
     * change the Vocabulary List of a Vocabulary
     * @param vocab the object that should be changed
     * @param newVocabList the new Vocablist
     */
    public void editVocabList(Vocab vocab,VocabList newVocabList);

    /**
     * change the items of a vocabulary
     * @param vocab the object that should be changed
     * @param newVocabs list with the new vocabularies of this object
     */
    public void editVocabs(Vocab vocab, List<String> newVocabs);

    /**
     * change the translation of a vocabulary
     * @param vocab the object that should be changed
     * @param translations the list with new translations of a vocabulary
     */
    public void editTranslations(Vocab vocab, List<Translation> translations);

}
