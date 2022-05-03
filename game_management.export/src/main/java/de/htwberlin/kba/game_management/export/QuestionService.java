package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

public interface QuestionService {
    /**
     * Takes the given answer from the user, validates if its correct and gives back if it was the correct answer or not
     * @param givenAnswer given answer from the user
     * @param user information on which user answered
     * @return gives information whether answer was correct (1) or not (0)
     */
    public Boolean answerQuestion(String givenAnswer, User user);
}
