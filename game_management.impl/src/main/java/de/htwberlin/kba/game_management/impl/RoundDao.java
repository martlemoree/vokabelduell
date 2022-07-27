package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomOptimisticLockExceptionGame;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
import de.htwberlin.kba.game_management.export.Round;

import java.util.List;

public interface RoundDao {

    void createRound(Round round);

    Round getRoundById(Long roundId) throws CustomObjectNotFoundException;

    void updateRound(Round round) throws CustomOptimisticLockExceptionGame;

    List<Round> getAllRounds();

    void deleteRound(Round round);

}
