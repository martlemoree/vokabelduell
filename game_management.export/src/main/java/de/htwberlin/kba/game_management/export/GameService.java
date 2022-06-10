package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;

import java.util.List;

public interface GameService {

    /**
     * creates a new Game.
     * @param requester is the user who sent the request for the game.
     * @param receiver is the user who received and accepted the game request.
     * @return a new game
     */
    Game createGame(User requester, User receiver);

    /**
     * method calculates points for given user and changes value accordingly
     * @param game game which is to be changed
     * @param user user for which points are calculated
     * @param points number of points
     */
    void calculatePoints(Game game, User user, int points);

    /**
     * To continue existing game, it is nessecary to get all games of the current user
     * @param user current user
     * @return list of all existing games of current user
     */
    List<Game> getGamesFromCurrentUser(User user);

}