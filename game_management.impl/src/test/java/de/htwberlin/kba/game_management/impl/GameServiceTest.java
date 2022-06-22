package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Spy
    @InjectMocks
    private GameServiceImpl service;
    @Mock
    private GameDaoImpl gameDao;
    @Mock
    private User requester;
    @Mock
    private User receiver;

    @Before
    public void setUp() {
        //this.service = new GameServiceImpl();
        this.requester = new User("MartinTheBrain", "lol123");
        this.receiver = new User("stellomello", "123lol");
    }

    @DisplayName("checks whether a Game is created correctly.")
    @Test
    public void testCreateGame() {
        //1. Arrange
        //s. Set Up

        //2. Act
        //Mockito.doNothing().when(gameDao).createGame(Mockito.any(Game.class));
        Game game = service.createGame(requester, receiver);

        //3. Assert
        Assert.assertNotNull(game);
        Assert.assertEquals(requester, game.getRequester());
        Assert.assertEquals(receiver, game.getReceiver());
    }

    @DisplayName("checks whether points are calculated correctly the first time points are added + for the correct user")
    @Test
    public void testCalculatePointsOnce() {
        // 1. Arrange
        Game game = new Game( requester, receiver);
        int newPoints = 500;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        service.calculatePoints(game, requester, newPoints);

        // 3. Assert
        Assert.assertEquals(newPoints, game.getPointsRequester());
    }

    @DisplayName("checks whether points are calculated correctly if added multiple times + for the correct user")
    @Test
    public void testCalculatePointsMultipleTimes() {
        // 1. Arrange
        Game game = new Game(requester, receiver);
        int newPoints = 500;
        int morePoints = 200;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        service.calculatePoints(game, receiver, newPoints);
        service.calculatePoints(game, receiver, morePoints);
        int sum = newPoints+morePoints;

        // 3. Assert
        Assert.assertEquals(sum, game.getPointsReceiver());
    }

    @DisplayName("checks whether the method returns a list with games where the number of rounds is <6")
    @Test
    public void getGamesFromCurrentUser() {
        // 1. Assert
        Game game1 = new Game();
        Game game2 = new Game();

        Round round1 = new Round();
        Round round2 = new Round();
        Round round3 = new Round();

        List<Round> rounds_3 = new ArrayList<>();
        rounds_3.add(round1);
        rounds_3.add(round2);
        rounds_3.add(round3);

        List<Round> rounds_2 = new ArrayList<>();
        rounds_3.add(round1);
        rounds_3.add(round2);


        game1.setRounds(rounds_3);
        game2.setRounds(rounds_2);

        List<Game> result_games = new ArrayList<>();
        result_games.add(game1);
        result_games.add(game2);

        User user = new User();

        boolean bol = true;

        // 2. Act
       // Mockito.when(gameDao.getAllGamesFromUser(Mockito.anyLong())).thenReturn(result_games);
        List<Game> gamesOfUser = service.getGamesFromCurrentUser(user);

        for (Game g:gamesOfUser) {
            if (g.getRounds().size() >= 6){
                bol = false;
                break;
            }
        }

        // 3. Assert
        Assert.assertNotNull(gamesOfUser);
        Assert.assertEquals(true, bol);
    }

}
