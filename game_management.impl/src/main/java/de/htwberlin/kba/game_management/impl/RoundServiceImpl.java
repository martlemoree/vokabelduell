package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.ArrayList;
import java.util.Scanner;

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
    public Round createRound(Long roundId, Game game, int currentRound) {
        return new Round(1L, game, currentRound);
    }

    public Round startNewRound(Game game) {
        List<Round> rounds = game.getRounds();
        Round round = createRound(1L, game, 1);
        rounds.add(round);
        // der list der round des games hinzufügen
        game.setRounds(rounds);

        return round;
    }
}
