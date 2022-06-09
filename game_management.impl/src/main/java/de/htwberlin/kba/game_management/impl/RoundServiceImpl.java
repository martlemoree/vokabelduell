package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {

    RoundDao roundDao;

    @Autowired
    public RoundServiceImpl(RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    @Override
    public Round createRound(Long roundId, Game game) {
        return new Round(1L, game);
    }

    public Round startNewRound(Game game) {
        List<Round> rounds = game.getRounds();
        Round round = createRound(1L, game);
        rounds.add(round);
        // der list der round des games hinzufügen
        game.setRounds(rounds);

        return round;
    }
}
