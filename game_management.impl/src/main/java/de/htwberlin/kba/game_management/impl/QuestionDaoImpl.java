package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Question;
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
    public Question getQuestionById(Long questionId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Question question = entityManager.find(Question.class, questionId);
        entityTransaction.commit();

        if (question == null) {
            throw new EntityNotFoundException("Can't find Question with questionId" + questionId);
        } else {
            return question;
        }
    }

    @Override
    public void updateQuestion(Question question) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(question);
        entityTransaction.commit();
    }

    @Override
    public List<Question> getAllQuestions() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Question> query = entityManager.createQuery("FROM Question AS questions", Question.class);
        List<Question> allQuestions = query.getResultList();
        entityTransaction.commit();
        return allQuestions;
    }

    @Override
    public void deleteQuestion(Question question) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(question);
        entityTransaction.commit();
    }

}
