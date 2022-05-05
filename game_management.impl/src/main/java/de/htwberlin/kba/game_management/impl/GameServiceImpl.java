package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

public class GameServiceImpl implements GameService {

    public void playGame() {
    }

    @Override
    public Game createGame(Long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound) {
        return null;
    }

    @Override
    public VocabList chooseVocabList() {
        return null;
    }

    @Override
    public Game createGame(long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound) {
        return null;
    }
}
