package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;

import java.util.List;

public interface GameDao {

    void createGame(Game game);

    Game getGameById(Long gameId);

    void updateGame(Game game);

    List<Game> getAllGames();

    void deleteGame(Game game);

    List<Game> getAllGamesFromUser(Long userId);
}
