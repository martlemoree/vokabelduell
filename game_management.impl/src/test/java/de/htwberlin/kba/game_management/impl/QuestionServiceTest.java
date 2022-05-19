package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionServiceImpl service;
    @Mock
    private UserService userService;
    @Mock
    private GameService gameService;
    @Mock
    private RoundService roundService;
    @Mock
    private QuestionService questionService;
    @Mock
    private User martin;
    private User stella;
    private Game game;
    private Round round;

    private Translation trans1;
    private Translation trans2;
    private Translation trans3;
    private Translation trans4;
    private List<String> list5;
    private List<String> list6;
    private List<String> list7;

    private VocabList wohnen;
    private Vocab baum;
    private Vocab haus;
    private Vocab garten;
    private Vocab dach;

    @Before
    public void setUp(){
        this.service = new QuestionServiceImpl();
        this.martin = new User(12345L, "MartinTheBrain", "lol123");
        this.stella = new User(934038L, "stellomello", "123lol");
        this.game = new Game(430920L,  martin, stella);
        this.round = new Round(583820L, game, martin, stella, 1);

        //Translation for wrongA  Set Up
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("tree");
        Translation trans1 = new Translation(4L, list1);

        //Translation for wrongB Set Up
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("home");
        Translation trans2 = new Translation(5L, list2);

        //Translation for wrongC Set Up
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("garden");
        list3.add("yard");
        Translation trans3 = new Translation(6L, list3);

        //Question Set Up
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("roof");
        Translation trans4 = new Translation(7L, list4);

        //wrongA SetUp
        this.list5 = new ArrayList<String>();
        list5.add("baum");

        //wrongB SetUp
        ArrayList<String> list6 = new ArrayList<String>();
        list6.add("haus");
        list6.add("heim");

        //wrongC SetUp
        ArrayList<String> list7 = new ArrayList<String>();
        list7.add("garten");

        //CorrectAnswer SetUp
        ArrayList<String> list8 = new ArrayList<String>();
        list8.add("dach");

        ArrayList<Translation> transList1 = new ArrayList<Translation>();
        transList1.add(trans1);
        ArrayList<Translation> transList2 = new ArrayList<Translation>();
        transList2.add(trans2);
        ArrayList<Translation> transList3 = new ArrayList<Translation>();
        transList3.add(trans3);
        ArrayList<Translation> transList4 = new ArrayList<Translation>();
        transList4.add(trans4);

        VocabList wohnen = new VocabList(456L, "Unit1", "Wohnen", "Englisch",null);
        Vocab baum = new Vocab(583902L, list5, wohnen, transList1);
        Vocab haus = new Vocab(506040L, list6, wohnen, transList2);
        Vocab garten = new Vocab(596L, list7, wohnen, transList3);
        Vocab dach = new Vocab(5670L, list8, wohnen, transList4);
    }

    /**
     * test to check question is true
     */

    @Test
    @DisplayName("question was answered correct")
    public void testAnswerQuestionCorrect(){
        // 1. Arrange
        // Mock-Objekte
        Long userMartinId = 12345L;
        Long userStellaId = 12346L;
        String answer = new String("dach");
        Question question = service.createQuestion(1L, martin, stella, game, round);
        question.setQuestion(trans4);
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);
        // Stubbing
        Mockito.when(userService.chooseUser(userMartinId)).thenReturn(martin);
        Mockito.when(userService.chooseUser(userStellaId)).thenReturn(stella);

        //2. Act
        boolean givenAnswer = service.answerQuestion(answer,dach,martin,stella,question);

        //3. Assert
        Assert.assertTrue(givenAnswer);

    }

    @Test
    @DisplayName("question was answered false")
    public void testAnswerQuestionFalse() {
        //1. Arrange
        String answer = new String("dach");
        Question question = service.createQuestion(1L, martin, stella, game, round);
        question.setQuestion(trans4);
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);

        //2. Act
        boolean givenAnswer = service.answerQuestion(answer, dach, martin, stella, question);

        //3. Assert
        Assert.assertFalse(givenAnswer);

    }

    @Test
    @DisplayName("A question is created correctly")
    public void testCreateQuestion() {
        //1. Arrange --> see setup

        //2. Act
        Question question = service.createQuestion(1L, martin, stella, game, round);

        //3. Assert
        Assert.assertNotNull(question);
        Assert.assertEquals(1L, question.getQuestionId());
        Assert.assertEquals(martin,question.getReceiver() );
        Assert.assertEquals(stella, question.getRequester());
        Assert.assertEquals(game, question.getGame());
        Assert.assertEquals(round, question.getRound());
    }
    @Test
    @DisplayName("The method returns a list with all answer options")
    public void testGetAllAnswers() {
        //1. Arrange --> see setup

        //2. Act
        Question question = service.createQuestion(1L, martin, stella, game, round);
        question.setQuestion(trans4);
        question.setRightAnswer(dach);
        question.setWrongA(baum);
        question.setWrongB(haus);
        question.setWrongC(garten);
        question.setRightAnswer(dach);
        List<Vocab> answerOptions = service.getAllAnswers();

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
        Question question = service.createQuestion(1L, martin, stella, game, round);

        //2. Act
        service.setAnswerOptions(question);

        //3. Assert
        Assert.assertNotNull(question.getRightAnswer());
        Assert.assertNotNull(question.getWrongA());
        Assert.assertNotNull(question.getWrongB());
        Assert.assertNotNull(question.getWrongC());
        Assert.assertNotNull(question.getQuestion());
    }



}
