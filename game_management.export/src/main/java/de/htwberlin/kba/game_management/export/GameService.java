package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabList;
import java.util.List;

public interface GameService {

    /**
     * create an object of this class
     * @param requester the user who sent the request
     * @param receiver the user who accepted the request
     * @return id of created Game
     */
    Long createGame(User requester, User receiver);

    /**
     * method for restController
     * @param gameId id of the game which should be updated with new points
     * @param userName user who made the points in a game
     * @param points points won/lost in a game
     * @throws UserNotFoundException - is thrown when the given user does not exist
     * @throws CustomObjectNotFoundException is thrown when the came cannot be found
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    void calculatePoints(Long gameId, String userName, int points) throws UserNotFoundException, CustomOptimisticLockExceptionGame, CustomObjectNotFoundException;

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
     * @param gameId id of game thats being played
     * @param currentUser user who plays
     * @param vocabList chosen vocabList by player or opponent
     * @return correct list of questions for the round
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    List<Question> giveQuestions(Long gameId, User currentUser, VocabList vocabList) throws CustomOptimisticLockExceptionGame;

    /**
     * get a game object by the given gameId
     * @param gameId given Id that should be searched for
     * @return the game with the given gameId
     * @throws CustomObjectNotFoundException is thrown when the game cannot be found
     */
    Game getGamebyId(Long gameId) throws CustomObjectNotFoundException;

//TODO löschen, nur für testzwecke
    List<Game> getALlGames();

}