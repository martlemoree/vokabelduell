package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;

public interface GameService {

    /**
     * Contains the game logic that one game consists of 6 rounds
     * @param game the game that should be played.
     */
    void playGame(Game game, User requester, User receiver);

    /**
     * creates a new Game.
     * @param gameId is the unique identifier of the game.
     * @param requester is the user who sent the request for the game.
     * @param receiver is the user who received and accepted the game request.
     * @return a new game
     */
    Game createGame(Long gameId, User requester, User receiver);

    /**
     * Takes the given answer from the user, validates if its correct and gives back if it was the correct answer or not
     * @param answer given answer from user
     * @param rightAnswer the right answer of the current question
     * @return gives information whether answer was correct (1) or not (0)
     */
    boolean answerQuestion(String answer, Translation rightAnswer);

    /**
     * method calculates points for given user and changes value accordingly
     * @param game game which is to be changed
     * @param user user for which points are calculated
     * @param points number of points
     */
    void calculatePoints(Game game, User user, int points);

}