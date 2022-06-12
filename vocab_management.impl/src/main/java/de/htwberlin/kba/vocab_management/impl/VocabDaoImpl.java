package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class VocabDaoImpl implements VocabDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocab(Vocab vocab) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(vocab);
        entityTransaction.commit();

    }

    @Override
    public Vocab getVocabById(Long vocabId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Vocab vocab = entityManager.find(Vocab.class, vocabId);
        entityTransaction.commit();
        if (vocab == null) {
            throw new EntityNotFoundException("Can't find Vocab with vocabId" + vocabId);
        } else {
            return vocab;
        }
    }

    @Override
    public void updateVocab(Vocab vocab) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(vocab);
        entityTransaction.commit();
    }

    @Override
    public List<Vocab> getAllVocabs() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Vocab> query = entityManager.createQuery("SELECT vocabs FROM Vocab AS vocabs", Vocab.class);
        List<Vocab> allVocabs = query.getResultList();
        entityTransaction.commit();
        return allVocabs;
    }

    @Override
    public void deleteVocab(Vocab vocab) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(vocab);
        entityTransaction.commit();
    }

}