package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;

import java.util.List;

public class VocabListServiceImpl implements VocabListService {
    @Override
    public VocabList createVocablist(Long vocablistId, String category, String name, String language, List<Vocab> vocabs) {
        return null;
    }

    @Override
    public void editName(String newName) {

    }

    @Override
    public void editLanguage(String newLanguage) {

    }

    @Override
    public void editCategory(String newCat) {

    }

    @Override
    public void removeVocab(Vocab vocab) {

    }

    @Override
    public void addVocab(Vocab vocab) {

    }

    @Override
    public VocabList chooseVocabList() {
        return null;
    }

    @Override
    public List<VocabList> getAllVocablists() {
        return null;
    }

}
