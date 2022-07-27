package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundDao roundDao;
    private GameDao gameDao;

    @Autowired
    public RoundServiceImpl(RoundDao roundDao, GameDao gameDao) {
        this.roundDao = roundDao;
        this.gameDao = gameDao;
    }

    // constructor without parameters needed for mockito testing
    public RoundServiceImpl() {}


    @Transactional
    public Round startNewRound(Game game) throws CustomLockException {
        List<Round> rounds = new ArrayList<>(); //das war schon richtig so

        if (game.getRounds() != null){
            rounds = game.getRounds();
        }
        Round round = new Round(game);
        roundDao.createRound(round);

        rounds.add(round);
        game.setRounds(rounds);
        gameDao.updateGame(game);


        return round;
    }

    @Transactional
    @Override
    public void changeLastPlayer(Long gameId, String userName) throws CustomObjectNotFoundException, CustomLockException {
        Game game = gameDao.getGameById(gameId);
        game.getRounds().get(game.getRounds().size() - 1).setLastUserPlayedName(userName);
        gameDao.updateGame(game);
    }

    @Override
    @Transactional
    public List<Round> getAllRounds(){
        List<Round> rounds = roundDao.getAllRounds();
        for (Round r: rounds){
            Hibernate.initialize(r.getGame().getRounds());
            Hibernate.initialize(r.getQuestions());
        }
        return rounds;
    }

    @Override
    @Transactional
    public Round getRoundById(Long id) throws CustomObjectNotFoundException {
        return roundDao.getRoundById(id);
    }


}
