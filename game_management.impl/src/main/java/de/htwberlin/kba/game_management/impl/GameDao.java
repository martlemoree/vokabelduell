package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomLockException;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.user_management.export.User;

import java.util.List;

public interface GameDao {

    void createGame(Game game);

    Game getGameById(Long gameId) throws CustomObjectNotFoundException;

    void updateGame(Game game) throws CustomLockException;

    List<Game> getAllGames();

    void deleteGame(Game game);


    List<Game> getAllGamesFromUser(User user);
}
