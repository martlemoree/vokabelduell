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
    public void editName(VocabList vocablist, String newName) {

    }

    @Override
    public void editLanguage(VocabList vocablist, String newLanguage) {

    }

    @Override
    public void editCategory(VocabList vocablist, String newCat) {

    }

    @Override
    public void removeVocab(VocabList vocablist, Vocab vocab) {

    }

    @Override
    public void addVocab(VocabList vocablist, Vocab vocab) {

    }
    @Override
    public List<VocabList> getRandomVocablists() {
        return null;
    }

}
