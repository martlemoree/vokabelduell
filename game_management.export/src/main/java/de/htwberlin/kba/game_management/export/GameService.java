package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabList;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface GameService {

    /**
     * create an object of this class
     * @param requester the user who sent the request
     * @param receiver the user who accepted the request
     * @return created Game
     */
    Game createGame(User requester, User receiver);

    /**
     * method calculates points for given user and changes value accordingly
     * @param gameId id of the game which should be updated with new points
     * @param userName user who made the points in a game
     * @param points points won/lost in a game
     * @throws UserNotFoundException - is thrown when the given user does not exist
     */
    void calculatePoints(Long gameId, String userName, int points) throws UserNotFoundException;


    /**
     * returns the games of the given user
     * @param userName of the user
     * @return the games of the given user
     * @throws UserNotFoundException - is thrown when the given user does not exist
     */
    List<Game> getGamesFromCurrentUser(String userName) throws UserNotFoundException;

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

//TODO löschen, nur für testzwecke
    List<Game> getALlGames();

}