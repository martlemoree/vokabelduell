package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.CustomOptimisticLockExceptionVocab;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class TranslationDaoImpl implements TranslationDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTranslation(Translation translation) {
        entityManager.persist(translation);
    }

    @Override
    public Translation getTranslationById(Long translationId) throws VocabListObjectNotFoundException {
        Translation translation;
        try {
            translation = entityManager.find(Translation.class, translationId);
        } catch (NoResultException e) {
            throw new VocabListObjectNotFoundException("Can't find Translation with translationId" + translationId);
        }
        return translation;
    }

    @Override
    public void updateTranslation(Translation translation) throws CustomOptimisticLockExceptionVocab {

        try {
            entityManager.merge(translation);
        } catch (OptimisticLockException e) {
            throw new CustomOptimisticLockExceptionVocab("Das Update konnte leider nicht durchgeführt werden und wird wiederholt.");
        }
    }

    @Override
    public List<Translation> getAllTranslations() {
        TypedQuery<Translation> query = entityManager.createNamedQuery("getAllTranslations", Translation.class);
        List<Translation> allTranslations = query.getResultList();
        return allTranslations;
    }

    @Override
    public void deleteTranslation(Long translationId) {
        entityManager.remove(translationId);
    }

    public List<Translation> getTranslationStrings(Long translationId) {
        TypedQuery<Translation> query = entityManager.createNamedQuery("getAllTranslationStrings", Translation.class);
        query.setParameter("translationId", translationId);
        List<Translation> translationString = query.getResultList();

        return translationString;
    }
}
