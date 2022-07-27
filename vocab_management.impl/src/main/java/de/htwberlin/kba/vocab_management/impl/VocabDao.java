package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.CustomOptimisticLockExceptionVocab;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;

import java.util.List;

public interface VocabDao {

    void createVocab(Vocab vocab);

    Vocab getVocabById(Long vocabId) throws VocabListObjectNotFoundException;

    void updateVocab(Vocab vocab) throws CustomOptimisticLockExceptionVocab;

    List<Vocab> getAllVocabs();

    void deleteVocab(Vocab vocab);

}
