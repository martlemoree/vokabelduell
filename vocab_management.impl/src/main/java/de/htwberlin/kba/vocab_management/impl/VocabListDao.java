package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface VocabListDao {

    void createVocabList(VocabList vocabList);

    VocabList getVocabListById(Long vocabListId);

    void updateVocabList(VocabList vocabList);

    List<VocabList> getAllVocabLists();

    void deleteVocabList(VocabList vocabList);


}
