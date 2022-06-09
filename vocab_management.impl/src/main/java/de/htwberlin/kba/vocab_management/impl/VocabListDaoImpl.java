package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Repository
public class VocabListDaoImpl implements VocabListDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createVocabList(VocabList vocabList) {
        entityManager.persist(vocabList);
    }

    @Override
    public VocabList getVocabListById(Long vocabListId) {
        VocabList vocabList = entityManager.find(VocabList.class, vocabListId);
        if (vocabList == null) {
            throw new EntityNotFoundException("Can't find VocabList with vocabListId" + vocabListId);
        } else {
            return vocabList;
        }
    }

    @Override
    public void updateVocabList(VocabList vocabList) {
        entityManager.merge(vocabList);
    }

    @Override
    public List<VocabList> getAllVocabLists() {
        TypedQuery<VocabList> query = entityManager.createQuery("SELECT vocablists FROM VocabList AS vocablists", VocabList.class);
        List<VocabList> allVocabLists = query.getResultList();
        return allVocabLists;
    }

    @Override
    public void deleteVocabList(VocabList vocabList) {
        entityManager.remove(vocabList);
    }

}
