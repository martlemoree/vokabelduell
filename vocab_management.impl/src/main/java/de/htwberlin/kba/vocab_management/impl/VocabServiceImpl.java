package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VocabServiceImpl implements VocabService {

    //todo würde ich wieder rausnehmen da vocabs nur über createvocablist erstellt werden
    @Override
    public Vocab createVocab(Long vocabId, List<String> vocabs, List<Translation> translations) {
        return new Vocab(vocabId,vocabs,translations );
    }

    @Override
    public void editVocabs(Vocab vocab, List<String> newVocabs) {
        vocab.setVocabs(newVocabs);
    }

    @Override
    public void editTranslations(Vocab vocab, List<Translation> translations) {
        vocab.setTranslations(translations);
    }

    public void removeVocab(){
        //todo dao das muss mit der datenbank gemacht werden muss
    }

    public Vocab getVocabByName(String vocabName) {
        //todo dao das muss mit der datenbank gemacht werden muss
        return null;
    }

    public String giveVocabStringRandom(Vocab vocab) {
        Random rand = new Random();

        int index = rand.nextInt(vocab.getVocabs().size()-1);
        return vocab.getVocabs().get(index);
    }


}
