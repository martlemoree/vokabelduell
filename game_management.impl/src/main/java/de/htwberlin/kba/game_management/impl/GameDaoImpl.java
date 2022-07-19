package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.user_management.export.User;
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
    public List<Game> getAllGamesFromUser(User user) {
        Long id = user.getUserId();
        TypedQuery<Game> query = entityManager.createNamedQuery("getAllGamesFromUser", Game.class);
        query.setParameter("userId", id);
        List<Game> allGamesFromUser = query.getResultList();
        return allGamesFromUser;
    }
}
