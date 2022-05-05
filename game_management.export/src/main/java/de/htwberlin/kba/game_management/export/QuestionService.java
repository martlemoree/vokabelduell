package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;

public interface QuestionService {
    /**
     * Takes the given answer from the user, validates if its correct and gives back if it was the correct answer or not
     * @param answer given answer from user
     * @param requester information on who gave the answer
     * @param receiver information on who gave the answer
     * @return gives information whether answer was correct (1) or not (0)
     */
    boolean answerQuestion(String answer, Vocab rigthAnswer, User requester, User receiver);
}
