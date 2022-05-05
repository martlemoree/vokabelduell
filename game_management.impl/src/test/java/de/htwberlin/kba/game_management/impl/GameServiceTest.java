package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.user_management.export.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class GameServiceTest {

    private GameService service;

    @Before
    public void setUp() {
        this.service = new GameServiceImpl();
    }

    @DisplayName("checks whether a Game is created correctly.")
    @Test
    public void testCreateGameServiceTest() {
        //1. Arrange
        Long gameId = Long.valueOf(123456);
        int pointsRequester = 0;
        int pointsReceiver = 0;
        User requester = new User(Long.valueOf(234567), "MartinTheBrain", "lol123");
        User receiver = new User(Long.valueOf(234568), "stellomello", "123lol");
        int currentRound = 1;

        //2. Act
        Game game = new Game(gameId, pointsRequester, pointsReceiver, requester, receiver, currentRound);

        //3. Assert
        Assert.assertNotNull(game);
        Assert.assertEquals(gameId, game.getGameId());
        Assert.assertEquals(pointsRequester, game.getPointsRequester());
        Assert.assertEquals(pointsReceiver, game.getPointsReceiver());
        Assert.assertEquals(requester, game.getRequester());
        Assert.assertEquals(receiver, game.getReceiver());
        Assert.assertEquals(currentRound, game.getCurrentRound());
    }

    @DisplayName("checks whether the game creates 6 rounds")
    @Test
    public void testPlayGame() {
        Long gameId = Long.valueOf(123456);
        int pointsRequester = 0;
        int pointsReceiver = 0;
        User requester = new User(Long.valueOf(234567), "MartinTheBrain", "lol123");
        ;
        User receiver = new User(Long.valueOf(234568), "stellomello", "123lol");
        int currentRound = 1;

        //2. Act
        Game game = new Game(gameId, pointsRequester, pointsReceiver, requester, receiver, currentRound);
        service.playGame();

        //3. Act
        Assert.assertEquals(6, game.getRounds().size());
    }

    @DisplayName("Rounds are being created during a game")
    @Test
    public void testCreateRounds(){
        Long gameId = Long.valueOf(123456);
        int pointsRequester = 0;
        int pointsReceiver = 0;
        User requester= new User(Long.valueOf(234567),"MartinTheBrain", "lol123");;
        User receiver = new User(Long.valueOf(234568),"stellomello", "123lol");
        int currentRound = 1;

        //2. Act
        Game game = new Game(gameId, pointsRequester, pointsReceiver, requester, receiver, currentRound);
        service.playGame();

        //3. Act
        Assert.assertNotNull(game.getRounds());
    }


}
