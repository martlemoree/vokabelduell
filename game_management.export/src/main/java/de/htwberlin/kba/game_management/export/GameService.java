package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

public interface GameService {

    /**
     * Contains the game logic that one game consists of 6 rounds
     * @param game the game that should be played.
     */
    void playGame(Game game);

    /**
     * creates a new Game.
     * @param gameId is the unique identifier of the game.
     * @param requester is the user who sent the request for the game.
     * @param receiver is the user who received and accepted the game request.
     * @return a new game
     */
    Game createGame(Long gameId, User requester, User receiver);

    /**
     * method calculates points for given user and changes value accordingly
     * @param game game which is to be changed
     * @param user user for which points are calculated
     * @param points number of points
     * @return
     */
    int calculatePoints(Game game, User user, int points);

}