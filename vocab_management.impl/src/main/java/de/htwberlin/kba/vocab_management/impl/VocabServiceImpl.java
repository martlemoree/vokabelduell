package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VocabServiceImpl implements VocabService {

    private VocabDao vocabDao;

    @Autowired
    public VocabServiceImpl(VocabDao vocabDao) {
        this.vocabDao = vocabDao;
    }

    //Constructor without parameters is needed for Mockito Tests
    public VocabServiceImpl() {}

    @Override
    public Vocab createVocab(Long vocabId, List<String> vocabs, List<Translation> translations) {
        // method not implmeneted and tested because it is not part of the game logic
        return new Vocab(vocabs,translations );
    }

    @Override
    public void editVocabs(Vocab vocab, List<String> newVocabs) {
        // method not implemented and tested because it is not part of the game logic
        vocab.setVocabs(newVocabs);
    }

    @Override
    public void editTranslations(Vocab vocab, List<Translation> translations) {
        // method not implemented and tested because it is not part of the game logic
        vocab.setTranslations(translations);
    }

    public void removeVocab(){
        // method not implemented and tested because it is not part of the game logic
    }

    public Vocab getVocabByVocabString(String vocabString) {
        // method not implemented and tested because it is not part of the game logic
        return null;
    }

    public String giveVocabStringRandom(Vocab vocab) {
        Random rand = new Random();

        int index = rand.nextInt(vocab.getVocabs().size()-1);
        return vocab.getVocabs().get(index);
    }
}
