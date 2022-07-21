package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
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

    @Autowired
    public RoundServiceImpl(RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    // constructor without parameters needed for mockito testing
    public RoundServiceImpl() {}

    @Transactional
    @Override
    public Round createRound(Game game){
        Round round = new Round(game);
        roundDao.createRound(round);
        return round;
    }


    //TODO das funktioniert so nicht, weil das sind 2 HTTP aufrufe in einem --> das muss ich zwei methoden getrennt werden
    // 2. methode ist im game service da ein game geupdatet wird

    @Transactional
    public Round startNewRound(Game game) {
        List<Round> rounds = new ArrayList<>(); //das war schon richtig so

        if (game.getRounds() != null){
            rounds = game.getRounds();
        }
        Round round = new Round(game);
        roundDao.createRound(round);

        rounds.add(round);
        game.setRounds(rounds);

        return round;
    }

    // TODO DAO???
    public void changeLastPlayer(Game game, User user) {
        game.getRounds().get(game.getRounds().size() - 1).setLastUserPlayedName(user.getUserName());
    }

    @Override
    public List<Round> getAllRounds(){
        List<Round> rounds = roundDao.getAllRounds();
        for (Round r: rounds){
            Hibernate.initialize(r.getGame().getRounds());
            Hibernate.initialize(r.getQuestions());
        }
        return rounds;
    }

    @Override
    public Round getRoundById(Long id) {
        return roundDao.getRoundById(id);
    }


}
