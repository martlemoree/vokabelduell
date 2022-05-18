package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface QuestionService {
    /**
     * Takes the given answer from the user, validates if its correct and gives back if it was the correct answer or not
     * @param answer given answer from user
     * @param rightAnswer the right answer of the current question
     * @param requester information on who gave the answer
     * @param receiver information on who gave the answer
     * @param question Question that should be changed
     * @return gives information whether answer was correct (1) or not (0)
     */
    boolean answerQuestion(String answer, Vocab rightAnswer, User requester, User receiver, Question question);

    /**
     * create a new question including the vocab and the correct translation from the chosen VocabList by the user
     * as well as three wrong answer options
     * @param questionId the unique identifier of the question
     * @param requester the first user that answers the question
     * @param receiver the second user that answers the question
     * @param game the game in which the question was created
     * @param round the round of a game in which the question is created
     * @return  a new question
     */
    Question createQuestion(Long questionId, User requester, User receiver, Game game, Round round, VocabList vocabList);

    /**
     * A translation is randomly generated for a wrong answer option to the question.
     */
    Translation setAnswerOptions();

    /**
     * should show all answer options for the question
     * where the entry at index 0 is the correct answer
     * @return the list of answer options
     */
    List<Translation> getAllAnswers(Question question);

}
