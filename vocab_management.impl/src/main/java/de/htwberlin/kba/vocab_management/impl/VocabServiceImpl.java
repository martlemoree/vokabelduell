package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.*;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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

    @Transactional
    public Vocab createVocab(List<String> vocabs, List<Translation> translations) {
        // method not implmeneted and tested because it is not part of the game logic
        Vocab vocab = new Vocab(vocabs,translations );
        vocabDao.createVocab(vocab);
        Hibernate.initialize(vocab.getTranslations());
        return vocab;
    }

    @Override
    @Transactional
    public void editVocabs(Vocab vocab, List<String> newVocabs) throws CustomOptimisticLockExceptionVocab {
        // method not implemented and tested because it is not part of the game logic
        vocab.setVocabs(newVocabs);
        vocabDao.updateVocab(vocab);
        Hibernate.initialize(vocab.getTranslations());
    }

    @Override
    @Transactional
    public void editTranslations(Vocab vocab, List<Translation> translations) throws CustomOptimisticLockExceptionVocab {
        // method not implemented and tested because it is not part of the game logic
        vocab.setTranslations(translations);
        vocabDao.updateVocab(vocab);
        Hibernate.initialize(vocab.getTranslations());
    }

    public void removeVocab(){
        // method not implemented and tested because it is not part of the game logic
    }

    public Vocab getVocabByVocabString(String vocabString) throws VocabListObjectNotFoundException {
        // method not implemented and tested because it is not part of the game logic
        return null;
    }
}
