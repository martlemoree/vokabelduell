package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    // constructor without parameters is needed for mockito testing
    public GameServiceImpl() {}

    @Override
    public Game createGame(User requester, User receiver) {
        Game game =  new Game (requester, receiver);
       // gameDao.createGame(game); // TODO Datenbank

        return game;
    }


    public void calculatePoints(Game game, User user, int points) {
        if (user.equals(game.getReceiver ())) {
            game.setPointsReceiver (points);
            // TODO Datenbank
        }
        if (user.equals(game.getRequester ())) {
            game.setPointsRequester (points);
            // TODO Datenbank
        }
    }

    public List<Game> getGamesFromCurrentUser(User user) {
        // TODO: DAO, am besten auch, wo die List<Round> weniger als 6 Einträge hat, aber nur wenn nicht zu viel Aufwand :)
        return null;
    }


}