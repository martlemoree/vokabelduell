package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.user_management.export.User;


public class GameServiceImpl implements GameService {

    @Override
    public void playGame(Game game) {
    }

    @Override
    public Game createGame(Long gameId, User requester, User receiver) {
        return null;
    }

    public int calculatePoints(Game game, User user, int points) { return 0; }

}