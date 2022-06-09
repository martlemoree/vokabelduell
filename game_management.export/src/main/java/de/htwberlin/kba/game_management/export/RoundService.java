package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface RoundService {

    /**
     * create a new round
     * @param roundId unique identifier of the round
     * @param game the game in which the round is created
     * @return a new round
     */
    Round createRound(Long roundId, Game game);

    /**
     * new round is created and directly added to the list of rounds of the current game
     *
     * @param game current game for which new round is needed
     * @return new round started
     */
    Round startNewRound(Game game);
}
