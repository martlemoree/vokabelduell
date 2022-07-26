package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class VocabListDaoImpl implements VocabListDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocabList(VocabList vocabList) {
        entityManager.persist(vocabList);
    }

    @Override
    public VocabList getVocabListById(Long vocabListId) throws VocabListNotFoundException {
        VocabList vocabList;
        try {
            vocabList = entityManager.find(VocabList.class, vocabListId);
        } catch (NoResultException e) {
            throw new VocabListNotFoundException("Die VocabList wurde nicht gefunden. Versuche es noch einmal");
        }
        return vocabList;
    }


    @Override
    public void updateVocabList(VocabList vocabList) {
        entityManager.merge(vocabList);
    }

    @Override
    public List<VocabList> getAllVocabLists() {
        TypedQuery<VocabList> query = entityManager.createNamedQuery("getAllVocabLists", VocabList.class);
        List<VocabList> allVocabLists = query.getResultList();
        return allVocabLists;
    }

    @Override
    public void deleteVocabList(VocabList vocabList) {
        entityManager.remove(vocabList);
    }

}
