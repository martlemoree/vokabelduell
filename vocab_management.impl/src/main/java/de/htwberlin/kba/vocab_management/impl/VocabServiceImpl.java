package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabService;

import java.util.List;

public class VocabServiceImpl implements VocabService {


    @Override
    public Vocab createVocab(Long vocabId, List<String> vocabs, VocabList vocablist, List<Translation> translations) {
        return null;
    }

    @Override
    public void editVocabList(Vocab vocab, VocabList newVocabList) {

    }

    @Override
    public void editVocabs(Vocab vocab, List<String> newVocabs) {

    }

    @Override
    public void editTranslations(Vocab vocab, List<Translation> translations) {

    }


}
