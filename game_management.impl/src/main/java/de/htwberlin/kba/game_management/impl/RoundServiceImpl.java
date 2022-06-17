package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
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
    public Round startNewRound(Game game) {
        List<Round> rounds = new ArrayList<>();

        if (game.getRounds() != null){
            rounds = game.getRounds();
        }
        Round round = new Round(game);
        roundDao.createRound(round);

        rounds.add(round);
        game.setRounds(rounds);

        return round;
    }
}
