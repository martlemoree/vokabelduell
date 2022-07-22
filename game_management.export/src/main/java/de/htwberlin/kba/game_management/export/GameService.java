package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface GameService {


    Game createGame(User requester, User receiver);


    /**
     * method calculates points for given user and changes value accordingly
     * @param game game which is to be changed
     * @param user user for which points are calculated
     * @param points number of points
     */
    void calculatePoints(Game game, User user, int points);


    /**
     * method for restController
     * @param gameId
     * @param userName
     * @param points
     */
    void calculatePoints(Long gameId, String userName, int points);


    List<Game> getGamesFromCurrentUser(String userName);

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
     * get a game object by the given gameId
     * @param gameId given Id that should be searched for
     * @return the game with the given gameId
     */
    Game getGamebyId(Long gameId);


    List<Game> getALlGames();

}