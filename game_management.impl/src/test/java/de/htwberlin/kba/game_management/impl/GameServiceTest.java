package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
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
    private VocabListService mockVocabListService;
    @Mock
    private GameDaoImpl gameDao;
    @Mock
    private RoundService mockRoundService;
    @Mock
    private QuestionService mockQuestionService;
    @Mock
    UserService userService;
    private User requester;
    private User receiver;
    private Game game;
    private Round round;
    List<Question> questions;
    VocabList vocabList = new VocabList();
    private List<Translation> translations = new ArrayList<>();
    Translation translation;
    Translation translation2;
    Translation translation3;
    Translation translation4;
    private List<VocabList> vocabLists = new ArrayList<>();
    private Vocab vocab;

    @Before
    public void setUp() {
        //this.gameService = new GameServiceImpl();
        this.requester = new User("MartinTheBrain", "lol123");
        this.receiver = new User("stellomello", "123lol");
        game = new Game( requester, receiver);
        game.setGameId(1L);
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


        vocabStrings.add("Vocab");
        vocabStrings.add("Vocab2");
        translationString = "Translation";
        translation = new Translation(translationStrings);
        translations.add(translation);

        List<String> translationStrings2 = Arrays.asList("Translation2");
        translation2 = new Translation(translationStrings2);
        List<String> translationStrings3 = Arrays.asList("Translation3");
        translation3 = new Translation(translationStrings3);
        List<String> translationStrings4 = Arrays.asList("Translation4");
        translation4 = new Translation(translationStrings4);

        translations.add(translation2);
        translations.add(translation3);
        translations.add(translation4);

        vocab = new Vocab(vocabStrings, translations);
        vocabs = new ArrayList<>();
        vocabs.add(vocab);

        translation.setVocabs(vocabs);
        vocabList = new VocabList("Category", "Name","Language", vocabs);

        vocabLists.add(vocabList);

        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        game.setRounds(rounds);

        question = new Question(round, translation, translation2, translation3, translation4, vocab);
        questions = new ArrayList<>();
        questions.add(question);
    }

    @DisplayName("checks whether a Game is created correctly.")
    @Test
    public void testCreateGame() throws CustomObjectNotFoundException {
        //1. Arrange
        //s. Set Up

        //2. Act
        //Mockito.doNothing().when(gameDao).createGame(Mockito.any(Game.class));
        Request request = new Request(Status.PENDING, requester, receiver);
        Long createdGameId = gameService.createGame(request.getRequester(), request.getReceiver());

        //3. Assert
        Assert.assertNotNull(createdGameId);
        Assert.assertEquals(requester, gameService.getGamebyId(createdGameId).getRequester());
        Assert.assertEquals(receiver, gameService.getGamebyId(createdGameId).getReceiver());
    }



    @DisplayName("checks whether points are calculated correctly the first time points are added + for the correct user")
    @Test
    public void testCalculatePointsOnce() throws UserNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        // 1. Arrange

        int newPoints = 500;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));

        when(gameDao.getGameById(Mockito.anyLong())).thenReturn(game);
        when(userService.getUserByUserName(Mockito.anyString())).thenReturn(requester);
        gameService.calculatePoints(game.getGameId(), requester.getUserName(), newPoints);

        // 3. Assert
        Assert.assertEquals(newPoints, game.getPointsRequester());
    }

    @DisplayName("checks whether points are calculated correctly if added multiple times + for the correct user")
    @Test
    public void testCalculatePointsMultipleTimes() throws UserNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        // 1. Arrange
        int newPoints = 500;
        int morePoints = 200;

        //2. Act
        //Mockito.doNothing().when(gameDao).updateGame(Mockito.any(Game.class));
        when(gameDao.getGameById(Mockito.anyLong())).thenReturn(game);
        when(userService.getUserByUserName(Mockito.anyString())).thenReturn(receiver);
        gameService.calculatePoints(game.getGameId(), receiver.getUserName(), newPoints);
        gameService.calculatePoints(game.getGameId(), receiver.getUserName(), morePoints);
        int sum = newPoints+morePoints;

        // 3. Assert
        Assert.assertEquals(sum, game.getPointsReceiver());
    }

    // Test wird grün wenn man ihn einzeln ausführt
    @DisplayName("checks whether the method returns a list with games where the number of rounds is <6")
    @Test
    public void getGamesFromCurrentUser() throws UserNotFoundException {
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
        when(userService.getUserByUserName(Mockito.anyString())).thenReturn(user);
        List<Game> gamesOfUser = gameService.getGamesFromCurrentUser(user.getUserName());

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
    public void testGiveQuestionsNoRoundsReturn() throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException, VocabListObjectNotFoundException {
        // 1. Arrange
        // s. setup

        //2. Act
        when(mockRoundService.startNewRound(Mockito.any(Game.class))).thenReturn(round);
        when(mockQuestionService.createQuestions(game, vocabList, round)).thenReturn(questions);


        List<List<String>> givenQuestions = gameService.giveQuestions(game.getGameId(), receiver.getUserName(), vocabList.getVocabListId());

        // 3. Assert
        Assert.assertNotNull(givenQuestions);
        Assert.assertFalse(round.getisPlayedByTwo());
    }

    @DisplayName("checks whether questions are returned when game already has rounds and game has not been played by both players")
    @Test
    public void testGiveQuestionsRoundsIsPlayedByTwoFalse() throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException, VocabListObjectNotFoundException {
        // 1. Arrange
        // s. setup
        List<Round> rounds = Arrays.asList(round);
        round.setQuestions(questions);
        game.setRounds(rounds);

        //2. Act
        List<List<String>> givenQuestions = gameService.giveQuestions(game.getGameId(), receiver.getUserName(), vocabList.getVocabListId());

        // 3. Assert
        Assert.assertTrue(round.getisPlayedByTwo());
        Assert.assertNotNull(givenQuestions);
    }

    @DisplayName("checks whether questions are returned when game already has rounds and game has already been played by both players")
    @Test
    public void testGiveQuestionsRoundsIsPlayedByTwoTrue() throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException, VocabListObjectNotFoundException {
        // 1. Arrange
        // s. setup

        //2. Act
        when(mockRoundService.startNewRound(Mockito.any(Game.class))).thenReturn(round);
        when(mockQuestionService.createQuestions(game, vocabList, round)).thenReturn(questions);

        List<List<String>> givenQuestions = gameService.giveQuestions(game.getGameId(), receiver.getUserName(), vocabList.getVocabListId());

        // 3. Assert
        Assert.assertFalse(round.getisPlayedByTwo());
        Assert.assertNotNull(givenQuestions);
    }

    @Test
    @DisplayName("the method should return a string that is not empty")
    public void testGiveVocabStringRandomNotNull() throws CustomObjectNotFoundException {
        //1. Arrange

        /*
        Candidates for new Question() are:   Question(Round round, Translation wrongA, Translation wrongB, Translation wrongC, Translation rightAnswer, Vocab vocab)
         */
        Question question = new Question(round, translation, translation2, translation3, translation4, vocab);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        String randomString = gameService.giveVocabStringRandom(question.getQuestionId());

        //2. Act & 3. Assert
        Assert.assertNotNull(randomString);
    }

    @Test
    @DisplayName("the method should return a reasonable string")
    public void testGiveVocabStringRandom() throws CustomObjectNotFoundException {
        //1. Arrange

        /*
        Candidates for new Question() are:   Question(Round round, Translation wrongA, Translation wrongB, Translation wrongC, Translation rightAnswer, Vocab vocab)
         */
        Question question = new Question(round, translation, translation2, translation3, translation4, vocab);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        String randomString = gameService.giveVocabStringRandom(question.getQuestionId());

        //2. Act & 3. Assert
        Assert.assertTrue(randomString.equals(vocab.getVocabs().get(0)) || randomString.equals(vocab.getVocabs().get(1)));
    }

    // List<String> giveAnswerOptionsRandom(Question question);
    @Test
    @DisplayName("method gives back List of string")
    public void testGiveAnswerOptionsRandomReturn() throws CustomObjectNotFoundException {
        // 1. Arrange
        // s. setup

        // 2. Act
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Question question = mockQuestionService.createQuestion(round, vocabList);


        Mockito.when(mockQuestionService.getAllAnswers(question)).thenReturn(translations);

        List<String> answerOptions = gameService.giveAnswerOptionsRandom(question.getQuestionId());

        // 3. Assert
        Assert.assertNotNull(answerOptions);
    }

    @Test
    @DisplayName("method gives back correct List of string")
    public void testGiveAnswerOptionsRandomReturnCorrect() throws CustomObjectNotFoundException {
        // 1. Arrange
        // s. setup
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Question question = mockQuestionService.createQuestion(round, vocabList);


        // 2. Act
        Mockito.when(mockQuestionService.getAllAnswers(question)).thenReturn(translations);
        List<String> answerOptions = gameService.giveAnswerOptionsRandom(question.getQuestionId());

        // 3. Assert
        Assert.assertTrue(answerOptions.contains("Translation"));
        Assert.assertTrue(answerOptions.contains("Translation2"));
        Assert.assertTrue(answerOptions.contains("Translation3"));
        Assert.assertTrue(answerOptions.contains("Translation4"));
    }
}