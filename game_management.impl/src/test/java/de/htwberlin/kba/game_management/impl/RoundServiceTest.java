package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RoundServiceTest {

    @Spy
    @InjectMocks
    private RoundServiceImpl service;

    @Mock
    private RoundDao roundDao;
    @Mock
    private GameDao gameDao;
    private User requester;
    private User receiver;
    private Game game;
    private Round round;

    @Before
    public void setUp() {
        this.requester = new User("MartinTheBrain", "lol123");
        this.receiver = new User("stellomello", "123lol");
        game = new Game( requester, receiver);
        game.setGameId(1L);
        round = new Round(game);
    }

    @DisplayName("checks whether the method returns a round")
    @Test
    public void testStartNewRound() throws CustomOptimisticLockExceptionGame {
        // 1. Arrange
        User requester = new User("MartinTheBrain", "lol123");
        User receiver = new User("stellomello", "123lol");
        Game game = new Game(requester, receiver);

        // 2. Act & Assert
        Mockito.doNothing().when(roundDao).createRound(Mockito.any(Round.class));
        Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));

        Assert.assertNotNull(service.startNewRound(game));
        Assert.assertEquals(service.startNewRound(game).getGame(), game);

    }

    @Test
    @DisplayName("check if user is last player")
    public void testSetLastPlayer() throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException {
        // 1. Arrange
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        game.setRounds(rounds);

        // 2. Act
        Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        when(gameDao.getGameById(Mockito.anyLong())).thenReturn(game);
        service.changeLastPlayer(game.getGameId(), requester.getUserName());

        // 3. Assert
        Assert.assertEquals(round.getLastUserPlayedName(), requester.getUserName());
    }
}