package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.CustomOptimisticLockExceptionVocab;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class VocabDaoImpl implements VocabDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocab(Vocab vocab) {
        entityManager.persist(vocab);
    }

    @Override
    public Vocab getVocabById(Long vocabId) throws VocabListObjectNotFoundException {
        Vocab vocab;
        try {
            vocab = entityManager.find(Vocab.class, vocabId);
        } catch (NoResultException e) {
            throw new VocabListObjectNotFoundException("Can't find Vocab with vocabId" + vocabId);
        }
        return vocab;
    }

    @Override
    public void updateVocab(Vocab vocab) throws CustomOptimisticLockExceptionVocab {
        try {
            entityManager.merge(vocab);
        } catch (OptimisticLockException e) {
            throw new CustomOptimisticLockExceptionVocab("Das Update der Vocab konnte nicht durchgeführt werden und wird wiederholt.");
        }
    }

    @Override
    public List<Vocab> getAllVocabs() {
        TypedQuery<Vocab> query = entityManager.createNamedQuery("getAllVocabs", Vocab.class);
        List<Vocab> allVocabs = query.getResultList();
        return allVocabs;
    }

    @Override
    public void deleteVocab(Vocab vocab) {
        entityManager.remove(vocab);
    }
}
