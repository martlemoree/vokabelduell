package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        Question question = entityManager.find(Question.class, questionId);
        if (question == null) {
            throw new EntityNotFoundException("Can't find Question with questionId" + questionId);
        } else {
            return question;
        }
    }

    @Override
    public void updateQuestion(Question question) {
        entityManager.merge(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        TypedQuery<Question> query = entityManager.createQuery("FROM Question AS questions", Question.class);
        List<Question> allQuestions = query.getResultList();
        return allQuestions;
    }

    @Override
    public void deleteQuestion(Question question) {
        entityManager.remove(question);
    }

}
