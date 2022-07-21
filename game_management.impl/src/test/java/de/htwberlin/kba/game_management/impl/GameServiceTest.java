package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Spy
    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private GameDaoImpl gameDao;
    @Mock
    private RoundService mockRoundService;
    @Mock
    private QuestionService mockQuestionService;
    private User requester;
    private User receiver;
    private Game game;
    private Round round;
    List<Question> questions;
    VocabList vocabList = new VocabList();

    @Before
    public void setUp() {
        //this.gameService = new GameServiceImpl();
        this.requester = new User("MartinTheBrain", "lol123");
        this.receiver = new User("stellomello", "123lol");
        game = new Game( requester, receiver);
        round = new Round(game);

        List<String> vocabStrings = Arrays.asList("Vocab");
        String translationString = "Translation";
        List<String> translationStrings = Arrays.asList(translationString);
        Translation translation = new Translation(translationStrings);
        List<Translation> translations = new ArrayList<>();
        translations.add(translation);
        Vocab vocab = new Vocab(vocabStrings, translations);
        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(vocab);
        vocabList = new VocabList("Category", "Name","Language", vocabs);
        Question question = new Question(round, translation, translation, translation, translation, vocab);

        questions= Arrays.asList(question);
    }

    @DisplayName("checks whether a Game is created correctly.")
    @Test
    public void testCreateGame() throws SQLException {
        //1. Arrange
        //s. Set Up

        //2. Act
        //Mockito.doNothing().when(gameDao).createGame(Mockito.any(Game.class));
        Request request = new Request(Status.PENDING, requester, receiver);
        Game createdGame = gameService.createGame(request);

        //3. Assert
        Assert.assertNotNull(createdGame);
        Assert.assertEquals(requester, createdGame.getRequester());
        Assert.assertEquals(receiver, createdGame.getReceiver());
    }

    @DisplayName("checks whether points are calculated correctly the first time points are added + for the correct user")
    @Test
    public void testCalculatePointsOnce() {
        // 1. Arrange

        int newPoints = 500;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        gameService.calculatePoints(game, requester, newPoints);

        // 3. Assert
        Assert.assertEquals(newPoints, game.getPointsRequester());
    }

    @DisplayName("checks whether points are calculated correctly if added multiple times + for the correct user")
    @Test
    public void testCalculatePointsMultipleTimes() {
        // 1. Arrange
        int newPoints = 500;
        int morePoints = 200;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        gameService.calculatePoints(game, receiver, newPoints);
        gameService.calculatePoints(game, receiver, morePoints);
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
        String userName = "test1";

        boolean bol = true;

        // 2. Act
       // Mockito.when(gameDao.getAllGamesFromUser(Mockito.anyLong())).thenReturn(result_games);
        List<Game> gamesOfUser = gameService.getGamesFromCurrentUser(userName);

        for (Game g:gamesOfUser) {
            if (g.getRounds().size() >= 6){
                bol = false;
                break;
            }
        }

        // 3. Assert
        Assert.assertNotNull(gamesOfUser);
        Assert.assertTrue(bol);
    }

    @DisplayName("checks whether questions are returned when game has no rounds yet")
    @Test
    public void testGiveQuestionsNoRoundsReturn() {
        // 1. Arrange
        // s. setup

        //2. Act
        when(mockRoundService.startNewRound(Mockito.any(Game.class))).thenReturn(round);
        when(mockQuestionService.createQuestions(game, vocabList, round)).thenReturn(questions);

        List<Question> givenQuestions = gameService.giveQuestions(game, receiver, vocabList);

        // 3. Assert
        Assert.assertNotNull(givenQuestions);
        Assert.assertFalse(round.getisPlayedByTwo());
    }

    @DisplayName("checks whether questions are returned when game already has rounds and game has not been played by both players")
    @Test
    public void testGiveQuestionsRoundsIsPlayedByTwoFalse() {
        // 1. Arrange
        // s. setup
        List<Round> rounds = Arrays.asList(round);
        round.setQuestions(questions);
        game.setRounds(rounds);

        //2. Act
        List<Question> givenQuestions = gameService.giveQuestions(game, receiver, vocabList);

        // 3. Assert
        Assert.assertTrue(round.getisPlayedByTwo());
        Assert.assertNotNull(givenQuestions);
    }

    @DisplayName("checks whether questions are returned when game already has rounds and game has already been played by both players")
    @Test
    public void testGiveQuestionsRoundsIsPlayedByTwoTrue() {
        // 1. Arrange
        // s. setup

        //2. Act
        when(mockRoundService.startNewRound(Mockito.any(Game.class))).thenReturn(round);
        when(mockQuestionService.createQuestions(game, vocabList, round)).thenReturn(questions);

        List<Question> givenQuestions = gameService.giveQuestions(game, receiver, vocabList);

        // 3. Assert
        Assert.assertFalse(round.getisPlayedByTwo());
        Assert.assertNotNull(givenQuestions);
    }
}
