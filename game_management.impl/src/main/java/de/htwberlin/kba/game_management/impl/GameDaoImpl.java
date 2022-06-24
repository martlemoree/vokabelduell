package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createGame(Game game) {
        entityManager.persist(game);
    }

    @Override
    public Game getGameById(Long gameId) {
        Game game = entityManager.find(Game.class, gameId);
        if (game == null) {
            throw new EntityNotFoundException("Can't find Game with gameId" + gameId);
        } else {
            return game;
        }
    }

    @Override
    public void updateGame(Game game) {
        entityManager.merge(game);
    }

    @Override
    public List<Game> getAllGames() {
        TypedQuery<Game> query = entityManager.createNamedQuery("getAllGames", Game.class);
        List<Game> allGames = query.getResultList();
        return allGames;
    }

    @Override
    public void deleteGame(Game game) {
        entityManager.remove(game);
    }

    @Override
    public List<Game> getAllGamesFromUser(Long userId) {
        TypedQuery<Game> query = entityManager.createNamedQuery("getAllGamesFromUser", Game.class);
        List<Game> allGamesFromUser = query.getResultList();
        return allGamesFromUser;
    }
}
