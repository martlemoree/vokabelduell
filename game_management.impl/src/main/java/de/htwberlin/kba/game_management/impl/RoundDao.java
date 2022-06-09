package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Round;

import java.util.List;

public interface RoundDao {

    void createRound(Round round);

    Round getRoundById(Long roundId);

    void updateRound(Round round);

    List<Round> getAllRounds();

    void deleteRound(Round round);

}
