package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        super();
        this.gameDao = gameDao;
    }

    // constructor without parameters is needed for mockito testing
    public GameServiceImpl() {}

    @Override
    public Game createGame(User requester, User receiver) {
        Game game =  new Game (requester, receiver);
        this.gameDao.createGame(game);
        return game;
    }


    public void calculatePoints(Game game, User user, int points) {
        if (user.equals(game.getReceiver ())) {
            game.setPointsReceiver (points);
        }
        if (user.equals(game.getRequester ())) {
            game.setPointsRequester (points);
        }
        gameDao.updateGame(game);
    }

    public List<Game> getGamesFromCurrentUser(User user) {
        List<Game> gamesFromUser = gameDao.getAllGamesFromUser(user.getUserId());

        for (Game g:gamesFromUser ) {
            if (g.getRounds().size() >= 6){
                gamesFromUser.remove(g);
            }
        }
        return gamesFromUser;
    }


}