package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {
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
