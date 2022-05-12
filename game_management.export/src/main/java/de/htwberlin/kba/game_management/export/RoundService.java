package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface RoundService {


    /**
     * the User gets a list of three randomly selected vocablists and can set the vocablist for the round
     * @return the vocablist of the current round
     */
    public VocabList chooseVocablist(List<VocabList> randomVocabLists);

    /**
     * create a new round
     * @param roundId unique identifier of the round
     * @param game the game in which the round is created
     * @param requester the 1st user that is playing in this round
     * @param receiver the 2nd user that is playing in this round
     * @param currentRound the number of the round in the current game
     * @return a new round
     */
    public Round createRound(Long roundId, Game game, User requester, User receiver, int currentRound);
}
