package de.htwberlin.kba.vocab_management.export;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface VocabService {

    /**
     * a new German Vocabulary is created.
     * @param vocabs is a list with the vocabulary and its different synonyms.
     * @param translations is a list of the different translation of the vocabulary. A vocabulary can have different meanings so that
     *                     there are several translations for every different meaning.
     * @return a new vocabulary
     */
    Vocab createVocab(List<String> vocabs, List<Translation> translations);

    /**
     * change the items of a vocabulary
     * @param vocab the object that should be changed
     * @param newVocabs list with the new vocabularies of this object
     * @throws CustomOptimisticLockExceptionVocab - is thrown in case of two users are working on the same object. The second user has to reload the object

     */
    void editVocabs(Vocab vocab, List<String> newVocabs) throws CustomOptimisticLockExceptionVocab;

    /**
     * change the translation of a vocabulary
     * @param vocab the object that should be changed
     * @param translations the list with new translations of a vocabulary
     * @throws CustomOptimisticLockExceptionVocab - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    void editTranslations(Vocab vocab, List<Translation> translations) throws CustomOptimisticLockExceptionVocab;

    /**
     * holds logic to identify and get a vocab by given string
     * @param vocabString given String
     * @return vocab with given name
     * @throws CustomOptimisticLockExceptionVocab - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    Vocab getVocabByVocabString(String vocabString) throws VocabListObjectNotFoundException;
}
