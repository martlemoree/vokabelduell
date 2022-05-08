package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public class GameServiceImpl implements GameService {

    @Override
    public void playGame() {
    }

    @Override
    public Game createGame(Long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver) {
        return null;
    }

}
