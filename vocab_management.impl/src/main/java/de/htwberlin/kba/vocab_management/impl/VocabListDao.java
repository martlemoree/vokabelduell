package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;

import java.util.List;

public interface VocabListDao {

    void createVocabList(VocabList vocabList);

    VocabList getVocabListById(Long vocabListId) throws VocabListObjectNotFoundException;

    void updateVocabList(VocabList vocabList);

    List<VocabList> getAllVocabLists();

    void deleteVocabList(VocabList vocabList);


}
