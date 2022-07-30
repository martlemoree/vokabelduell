package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;

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
    List<Long> getGamesFromCurrentUser(String userName) throws UserNotFoundException;

    /**
     * returns questions for current game for two cases:
     * old round has to be finished
     * new round has to be created
     * @param gameId id of game thats being played
     * @param userName user who plays
     * @param vocabListId chosen vocabList by player or opponent
     * @return List with the questions.
     *          each list element (=question) contains one question to be ask.
     *          The Strings in the list are in a specific order
     *          first comes the vocab which forms the question
     *          then the four answer possibilies
     *          last the question id to be able to see which answer option was corret
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    List<List<String>> giveQuestions(Long gameId, String userName, Long vocabListId) throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException, VocabListObjectNotFoundException;

    /**
     * get a game object by the given gameId
     * @param gameId given Id that should be searched for
     * @return the game with the given gameId
     * @throws CustomObjectNotFoundException is thrown when the game cannot be found
     */
    Game getGamebyId(Long gameId) throws CustomObjectNotFoundException;

    /**
     * the answer options for a question should not always be given in the same order
     * e.g. the correct answer is always the first given answer
     * therefore the answer options should be given randomly
     * @param questionId from questions the question which should be asked
     * @return a string list with the answer options
     * @throws CustomObjectNotFoundException is thrown when a game cannot be found
     */
    List<String> giveAnswerOptionsRandom(Long questionId) throws CustomObjectNotFoundException;

//TODO löschen, nur für testzwecke
    List<Game> getALlGames();

    /**
     * holds logic to give a random entry from the list of strings of the given vocab
     * @param questionId from questions that should be addressed
     * @return random string entry from list of strings of vocab
     * @throws CustomObjectNotFoundException is thrown when the game cannot be found
     */
    String giveVocabStringRandom(Long questionId) throws CustomObjectNotFoundException;

}