package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import java.util.ArrayList;

public class QuestionServiceTest {

    private QuestionService service;

    @Before
    public void setUp(){
        this.service = new QuestionServiceImpl();
    }

    /**
     * test to check question is true
     */

    @Test
    @DisplayName("question was answered correct")
    public void testAnswerQuestionCorrect(){
        //1. Arrange
        User martin = new User(Long.valueOf(12345),"MartinTheBrain", "lol123");
        User stella = new User(Long.valueOf(934038),"stellomello", "123lol");
        Game game = new Game(Long.valueOf(430920),0,0,martin,stella,1);
        Round round = new Round(Long.valueOf(583820), game, 0,0,martin,stella,1);
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("tree");
        Translation trans1 = new Translation(Long.valueOf(4), list1);
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("home");
        Translation trans2 = new Translation(Long.valueOf(5), list2);
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("garden");
        list3.add("yard");
        Translation trans3 = new Translation(Long.valueOf(6), list3);
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("roof");
        Translation trans4 = new Translation(Long.valueOf(7), list4);
        ArrayList<String> list5 = new ArrayList<String>();
        list5.add("baum");
        ArrayList<String> list6 = new ArrayList<String>();
        list6.add("haus");
        list6.add("heim");
        ArrayList<String> list7 = new ArrayList<String>();
        list7.add("garten");
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
        VocabList wohnen = new VocabList(Long.valueOf(456), "Unit1", "Wohnen", "Englisch",null);
        Vocab baum = new Vocab(Long.valueOf(583902), list5, wohnen, transList1);
        Vocab haus = new Vocab(Long.valueOf(506040), list6, wohnen, transList2);
        Vocab garten = new Vocab(Long.valueOf(596), list7, wohnen, transList3);
        Vocab dach = new Vocab(Long.valueOf(5670), list8, wohnen, transList4);
        Question question = new Question(Long.valueOf(1),martin, stella, game, round, baum, haus, garten, dach);

        String answer = new String("dach");

        //2. Act
        boolean givenAnswer = service.answerQuestion(answer,dach,martin,stella);

        //3. Assert
        Assert.assertTrue(givenAnswer);

    }

    @Test
    @DisplayName("question was answered false")
    public void testAnswerQuestionFalse() {
        //1. Arrange
        User martin = new User(Long.valueOf(12345), "MartinTheBrain", "lol123");
        User stella = new User(Long.valueOf(934038), "stellomello", "123lol");
        Game game = new Game(Long.valueOf(430920), 0, 0, martin, stella, 1);
        Round round = new Round(Long.valueOf(583820), game, 0, 0, martin, stella, 1);
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("tree");
        Translation trans1 = new Translation(Long.valueOf(4), list1);
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("home");
        Translation trans2 = new Translation(Long.valueOf(5), list2);
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("garden");
        list3.add("yard");
        Translation trans3 = new Translation(Long.valueOf(6), list3);
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("roof");
        Translation trans4 = new Translation(Long.valueOf(7), list4);
        ArrayList<String> list5 = new ArrayList<String>();
        list5.add("baum");
        ArrayList<String> list6 = new ArrayList<String>();
        list6.add("haus");
        list6.add("heim");
        ArrayList<String> list7 = new ArrayList<String>();
        list7.add("garten");
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
        VocabList wohnen = new VocabList(Long.valueOf(456), "Unit1", "Wohnen", "Englisch", null);
        Vocab baum = new Vocab(Long.valueOf(583902), list5, wohnen, transList1);
        Vocab haus = new Vocab(Long.valueOf(506040), list6, wohnen, transList2);
        Vocab garten = new Vocab(Long.valueOf(596), list7, wohnen, transList3);
        Vocab dach = new Vocab(Long.valueOf(5670), list8, wohnen, transList4);
        Question question = new Question(Long.valueOf(1), martin, stella, game, round, baum, haus, garten, dach);

        String answer = new String("baum");

        //2. Act
        boolean givenAnswer = service.answerQuestion(answer, dach, martin, stella);

        //3. Assert
        Assert.assertFalse(givenAnswer);

    }
}
