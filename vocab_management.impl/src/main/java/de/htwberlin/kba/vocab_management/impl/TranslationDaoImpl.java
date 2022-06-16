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
        entityManager.persist(translation);
    }

    @Override
    public Translation getTranslationById(Long translationId) {
        Translation translation = entityManager.find(Translation.class, translationId);
        if (translation == null) {
            throw new EntityNotFoundException("Can't find Translation with translationId" + translationId);
        } else {
            return translation;
        }
    }

    @Override
    public void updateTranslation(Translation translation) {
        entityManager.merge(translation);
    }

    @Override
    public List<Translation> getAllTranslations() {
        TypedQuery<Translation> query = entityManager.createNamedQuery("getAllTranslations", Translation.class);
        List<Translation> allTranslations = query.getResultList();
        return allTranslations;
    }

    @Override
    public void deleteTranslation(Translation translation) {
        entityManager.remove(translation);
    }
}
