package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;

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
}
