package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Question;

import java.util.List;

public interface QuestionDao {

    void createQuestion(Question question);

    Question getQuestionById(Long questionId);

    void updateQuestion(Question question);

    List<Question> getAllQuestions();

    void deleteQuestion(Question question);

}
