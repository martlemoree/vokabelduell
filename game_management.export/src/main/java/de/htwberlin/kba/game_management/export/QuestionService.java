package de.htwberlin.kba.game_management.export;

public interface QuestionService {
    /**
     * Takes the given answer from the user, validates if its correct and gives back if it was the correct answer or not
     * @param given answer from the user
     * @param information on which user answered
     * @return gives information whether answer was correct (1) or not (0)
     */
    public Boolean answerQuestion(String givenAnswer, User user);
}
