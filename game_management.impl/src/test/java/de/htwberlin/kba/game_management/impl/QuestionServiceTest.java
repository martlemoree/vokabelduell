package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Spy // object is partially mocked. the real methods are being called
    @InjectMocks
    private QuestionService questionService = new QuestionServiceImpl();

    @Mock
    private VocabListService mockVocabListService;
    @Mock
    private QuestionDao mockQuestionDao;
    private Round Round;
    private VocabList vocabList;
    private Translation translation;
    private Vocab vocab;



    @Before
    public void setUp(){
        User requester = new User(1L, "Peter", "Test");
        User receiver = new User(2L, "AuchPeter", "Test123");
        Game game = new Game(1L, requester, receiver);
        Round = new Round(1L, game);
        List<String> vocabStrings = Arrays.asList("Vocab");
        List<String> translationStrings = Arrays.asList("Translation");
        translation = new Translation(1L, translationStrings);
        List<Translation> translations = Arrays.asList(translation);

        vocab = new Vocab(1L, vocabStrings, translations);
        List<Vocab> Vocabs = Arrays.asList(vocab);
        vocabList = new VocabList(1L,"Category", "Name","Language", Vocabs);

        List<VocabList> VocabLists = Arrays.asList(vocabList);
    }

    // List<Question> createQuestions(Game game, VocabList chosenVocabList);
    @Test
    @DisplayName("A question is created")
    public void testCreateQuestion() {
         //1. Arrange
        // s. setup

        //2. Act
        Mockito.when(questionService.setAnswerOptions()).thenReturn(translation);

        Question question = questionService.createQuestion(1L, Round, vocabList);

        //3. Assert
        Assert.assertNotNull(question);
    }

    @Test
    @DisplayName("A question is created Correctly")
    public void testCreateQuestionCorrectly() {
        //1. Arrange
        // s. setup

        //2. Act
        Mockito.when(questionService.setAnswerOptions()).thenReturn(translation);

        Question question = questionService.createQuestion(1L, Round, vocabList);

        //3. Assert
        Assert.assertEquals(1L, question.getQuestionId());
        Assert.assertEquals(Round, question.getRound());
        Assert.assertEquals(translation, question.getRightAnswer());
        Assert.assertEquals(vocab, question.getVocab());
    }


    /* methods to test:


        already (kind of) tested
        boolean answeredQuestion(String answer, Translation rightAnswer);
        List<Translation> getAllAnswers(Question question);
        Question createQuestion(Long questionId, Round);
        void createAnswerOptions(int index);
     */


    // List<String> giveAnswerOptionsRandom(Question question);
    @Test
    @DisplayName("method gives back List of string")
    public void testGiveAnswerOptionsRandom(){
        //1. Arrange
        // s. setup


        //2. Act

        //3. Assert

    }
/*
    @Test
    @DisplayName("question was answered correct")
    public void testAnsweredQuestionCorrect(){
        //1. Arrange
        String answer = new String("dach");
        Question question = questionService.createQuestion(1L, martin, stella, game, round);
        question.setQuestions(trans4); // setQuestions gehört jetzt zur Round
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);

        //2. Act
        boolean givenAnswer = questionService.answerQuestion(answer,dach,martin,stella, question);

        //3. Assert
        Assert.assertTrue(givenAnswer);

    }

    @Test
    @DisplayName("question was answered false")
    public void testAnsweredQuestionFalse() {
        //1. Arrange
        String answer = new String("dach");
        Question question = questionService.createQuestion(1L, martin, stella, game, round);
        question.setQuestion(trans4);
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);

        //2. Act
        boolean givenAnswer = questionService.answerQuestion(answer, dach, martin, stella, question);

        //3. Assert
        Assert.assertFalse(givenAnswer);

    }


    @Test
    @DisplayName("The method returns a list with all answer options")
    public void testGetAllAnswers() {
        //1. Arrange --> see setup

        //2. Act
        Question question = questionService.createQuestion(1L, martin, stella, game, round);
        question.setQuestion(trans4);
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);
        List<Vocab> answerOptions = questionService.getAllAnswers();

        //3. Assert
        Assert.assertNotNull(answerOptions);
        Assert.assertEquals(4, answerOptions.size());
        Assert.assertTrue(answerOptions.contains(baum));
        Assert.assertTrue(answerOptions.contains(haus));
        Assert.assertTrue(answerOptions.contains(garten));
        Assert.assertTrue(answerOptions.contains(dach));
    }

    @Test
    @DisplayName("The method generates a question and 4 answer options and saves these data in the question object")
    public void testSetAnswerOption() {
        //1. Arrange --> see setup
        Question question = questionService.createQuestion(1L, martin, stella, game, round);

        //2. Act
        questionService.setAnswerOptions(question);

        //3. Assert
        Assert.assertNotNull(question.getRightAnswer());
        Assert.assertNotNull(question.getWrongA());
        Assert.assertNotNull(question.getWrongB());
        Assert.assertNotNull(question.getWrongC());
        Assert.assertNotNull(question.getQuestion());
    }


    /*
        @DisplayName("checks whether the game creates 6 rounds")
    @Test
    public void testPlayGame() {
        Long gameId = 123456L;
        User requester = new User(234567L, "MartinTheBrain", "lol123");
        User receiver = new User(234568L, "stellomello", "123lol");

        //2. Act
        Game game = new Game(gameId, requester, receiver);
        service.playGame(game);

        //3. Act
        Assert.assertEquals(6, game.getRounds().size());
    }

    @DisplayName("Rounds are being created during a game")
    @Test
    public void testPlayGameRoundsAreCreated(){
        Long gameId = 123456L;
        User requester= new User(234567L,"MartinTheBrain", "lol123");
        User receiver = new User(234568L,"stellomello", "123lol");

        //2. Act
        Game game = new Game(gameId, requester, receiver);
        service.playGame(game);

        //3. Act
        Assert.assertNotNull(game.getRounds());
    }
*/

}

