package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VocabDaoImpl implements VocabDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocab(Vocab vocab) {
        entityManager.persist(vocab);
    }

    @Override
    public Vocab getVocabById(Long vocabId) {
        Vocab vocab = entityManager.find(Vocab.class, vocabId);
        if (vocab == null) {
            throw new EntityNotFoundException("Can't find Vocab with vocabId" + vocabId);
        } else {
            return vocab;
        }
    }

    @Override
    public void updateVocab(Vocab vocab) {
        entityManager.merge(vocab);
    }

    @Override
    public List<Vocab> getAllVocabs() {
        TypedQuery<Vocab> query = entityManager.createQuery("SELECT vocabs FROM Vocab AS vocabs", Vocab.class);
        List<Vocab> allVocabs = query.getResultList();
        return allVocabs;
    }

    @Override
    public void deleteVocab(Vocab vocab) {
        entityManager.remove(vocab);
    }

}
