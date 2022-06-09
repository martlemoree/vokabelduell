package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;

import java.util.List;

public interface VocabDao {

    void createVocab(Vocab vocab);

    Vocab getVocabById(Long vocabId);

    void updateVocab(Vocab vocab);

    List<Vocab> getAllVocabs();

    void deleteVocab(Vocab vocab);
}
