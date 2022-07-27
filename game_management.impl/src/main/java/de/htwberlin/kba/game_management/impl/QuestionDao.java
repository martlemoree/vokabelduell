package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomLockException;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
import de.htwberlin.kba.game_management.export.Question;

import java.util.List;

public interface QuestionDao {

    void createQuestion(Question question);

    Question getQuestionById(Long questionId) throws CustomObjectNotFoundException;

    void updateQuestion(Question question) throws CustomLockException;

    List<Question> getAllQuestions();

    void deleteQuestion(Question question);

}
