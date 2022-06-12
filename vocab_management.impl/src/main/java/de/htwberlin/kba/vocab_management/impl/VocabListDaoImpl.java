package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Repository
public class VocabListDaoImpl implements VocabListDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocabList(VocabList vocabList) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(vocabList);
        entityTransaction.commit();
    }

    @Override
    public VocabList getVocabListById(Long vocabListId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        VocabList vocabList = entityManager.find(VocabList.class, vocabListId);
        entityTransaction.commit();
        if (vocabList == null) {
            throw new EntityNotFoundException("Can't find VocabList with vocabListId" + vocabListId);
        } else {
            return vocabList;
        }

    }


    @Override
    public void updateVocabList(VocabList vocabList) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(vocabList);
        entityTransaction.commit();

    }

    @Override
    public List<VocabList> getAllVocabLists() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<VocabList> query = entityManager.createQuery("SELECT vocablists FROM VocabList AS vocablists", VocabList.class);
        List<VocabList> allVocabLists = query.getResultList();
        entityTransaction.commit();
        return allVocabLists;
    }

    @Override
    public void deleteVocabList(VocabList vocabList) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(vocabList);
        entityTransaction.commit();
    }

}