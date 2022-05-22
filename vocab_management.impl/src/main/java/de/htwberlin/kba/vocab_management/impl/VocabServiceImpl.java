package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabServiceImpl implements VocabService {

    //todo würde ich wieder rausnehmen da vocabs nur über createvocablist erstellt werden

    @Override
    public Vocab createVocab(Long vocabId, List<String> vocabs, List<Translation> translations) {
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
