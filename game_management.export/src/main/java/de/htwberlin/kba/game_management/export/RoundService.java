package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import javax.transaction.Transactional;
import java.util.List;

public interface RoundService {

    @Transactional
    Round createRound(Game game);

    /**
     * new round is created and directly added to the list of rounds of the current game
     *
     * @param game current game for which new round is needed
     * @return new round started
     */
    Round startNewRound(Game game);

    /**
     * change the player who last played a round in the game
     * @param game current game
     * @param user current user
     */
    void changeLastPlayer(Game game, User user);

    List<Round> getAllRounds();

    Round getRoundById(Long id);
}
