package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface RoundService {

    /**
     * create a new round
     * @param roundId unique identifier of the round
     * @param game the game in which the round is created
     * @param currentRound the number of the round in the current game
     * @return a new round
     */
    Round createRound(Long roundId, Game game, int currentRound);

    // TODO: Javadoc
    Round startNewRound(Game game);
}
