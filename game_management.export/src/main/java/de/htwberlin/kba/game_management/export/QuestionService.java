package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;

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
     * create a new question
     * @param questionId the unique identifier of the question
     * @param requester the first user that answers the question
     * @param receiver the second user that answers the question
     * @param game the game in which the question was created
     * @param round the round of a game in which the question is created
     * @param wrongA 1st false answer option in german
     * @param wrongB 2nd false answer option in german
     * @param wrongC 3rd false answer option in german
     * @param rightAnswer is the right vocabulary for the question in german
     * @param question is a translation to which the correct vocab should be found
     * @return
     */
    public Question createQuestion(Long questionId, User requester, User receiver, Game game, Round round, Vocab wrongA, Vocab wrongB, Vocab wrongC, Vocab rightAnswer, Translation question);

    /**
     * should show all answer options for the question
     * @return the list of answer options
     */
    public List<Vocab> getAllAnswers();

}
