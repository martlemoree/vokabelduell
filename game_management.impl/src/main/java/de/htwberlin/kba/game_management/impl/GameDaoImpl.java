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

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Game game = entityManager.find(Game.class, gameId);
        entityManager.persist(game);
        entityTransaction.commit();

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

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Game> query = entityManager.createQuery("FROM Game AS games", Game.class);
        List<Game> allGames = query.getResultList();
        entityTransaction.commit();

        return allGames;
    }

    @Override
    public void deleteGame(Game game) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(game);
        entityTransaction.commit();
    }

    @Override
    public List<Game> getAllGamesFromUser(Long gameId) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Game> query = entityManager.createQuery("FROM Game AS games WHERE games.requester_id = gameId OR games.receiver_id = gameId ", Game.class);
        List<Game> allGames = query.getResultList();
        entityTransaction.commit();

        return allGames;
    }
}
