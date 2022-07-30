package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomOptimisticLockExceptionGame;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createQuestion(Question question) {
        entityManager.persist(question);
    }

    @Override
    public Question getQuestionById(Long questionId) throws CustomObjectNotFoundException {
        Question question;
        try {
            question = entityManager.find(Question.class, questionId);
        } catch (NoResultException e) {
            throw new CustomObjectNotFoundException("Can't find Question with questionId" + questionId);
        }
        return question;
    }

    @Override
    public void updateQuestion(Question question) throws CustomOptimisticLockExceptionGame {
        try {
            entityManager.merge(question);
        } catch (OptimisticLockException e) {
            throw new CustomOptimisticLockExceptionGame("\"Das Update konnte leider nicht durchgeführt werden. Bitte versuche es noch einmal.");
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        TypedQuery<Question> query = entityManager.createNamedQuery("getAllQuestions", Question.class);
        List<Question> allQuestions = query.getResultList();
        return allQuestions;
    }

    @Override
    public Translation getRightAnswer(Long questionId) {
        TypedQuery<Translation> query = entityManager.createNamedQuery("getRightAnswer", Translation.class);
        query.setParameter("questionId", questionId);
        Translation translation = query.getSingleResult();
        return translation;
    }

    @Override
    public void deleteQuestion(Question question) {
        entityManager.remove(question);
    }
}
