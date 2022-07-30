package de.htwberlin.kba.game_management.export;

import java.util.List;

public interface RoundService {

    /**
     * new round is created and directly added to the list of rounds of the current game
     *
     * @param game current game for which new round is needed
     * @return new round started
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    Round startNewRound(Game game) throws CustomOptimisticLockExceptionGame;

    /**
     * change the player who last played a round in the game
     * @param gameId - id of the current game
     * @param userName - name of the current user
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     * @throws CustomObjectNotFoundException is thrown when the round cannot be found
     */
    void changeLastPlayer(Long gameId, String userName) throws CustomObjectNotFoundException, CustomOptimisticLockExceptionGame;

    // TODO löschen nur für tests
    List<Round> getAllRounds();

    /**
     * find the round with the given id
     * @param id that should be searched
     * @return the round with given id
     * @throws CustomObjectNotFoundException is thrown when the round cannot be found
     */
    Round getRoundById(Long id) throws CustomObjectNotFoundException;
}
