package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public class RoundServiceImpl implements RoundService {


    @Override
    public VocabList chooseVocablist(List<VocabList> randomVocabLists) {
        return null;
    }

    @Override
    public Round createRound(Long roundId, Game game, User requester, User receiver, int currentRound) {
        return null;
    }
}
