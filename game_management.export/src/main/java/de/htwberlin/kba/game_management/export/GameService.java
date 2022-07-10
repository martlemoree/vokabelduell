package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface GameService {

    /**
     * creates a new Game.
     * @param request where to get the receiver and requester from
     * @return a new game
     */
    Game createGame(Request request);

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

    /**
     * returns questions for current game for two cases:
     * old round has to be finished
     * new round has to be created
     * @param game game thats being played
     * @param currentUser user who plays
     * @param vocabList chosen vocabList by player or opponent
     * @return correct list of questions for the round
     */
    List<Question> giveQuestions(Game game, User currentUser, VocabList vocabList);

    /**
     * for post request in game controller
     * @param game that should be created
     */
    void createGame(Game game);


}