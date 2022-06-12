package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class TranslationDaoImpl implements TranslationDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTranslation(Translation translation) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(translation);
        entityTransaction.commit();
    }

    @Override
    public Translation getTranslationById(Long translationId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Translation translation = entityManager.find(Translation.class, translationId);
        entityTransaction.commit();
        if (translation == null) {
            throw new EntityNotFoundException("Can't find Translation with translationId" + translationId);
        } else {
            return translation;
        }
    }

    @Override
    public void updateTranslation(Translation translation) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(translation);
        entityTransaction.commit();
    }

    @Override
    public List<Translation> getAllTranslations() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Translation> query = entityManager.createQuery("SELECT translations FROM Translation AS translations", Translation.class);
        List<Translation> allTranslations = query.getResultList();
        entityTransaction.commit();
        return allTranslations;
    }

    @Override
    public void deleteTranslation(Translation translation) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(translation);
        entityTransaction.commit();
    }

}
