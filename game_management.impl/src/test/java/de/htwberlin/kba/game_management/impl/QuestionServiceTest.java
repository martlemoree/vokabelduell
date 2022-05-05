package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;
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
    public void testAnswerQuestion(){
        //1. Arrange
        User martin = new User(Long.valueOf(12345),"MartinTheBrain", "lol123");
        User stella = new User(Long.valueOf(934038),"stellomello", "123lol");
        Game game = new Game(Long.valueOf(430920),0,0,martin,stella,1);
        Round round = new Round(Long.valueOf(583820), game, 0,0,martin,stella,1)
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("baum");
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("haus");
        list2.add("heim");
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("garten");
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("dach");
        Vocab baum = new Vocab(Long.valueOf(5839020),list1,"Wohnen",);
        Vocab haus = new Vocab(Long.valueOf(506040),list2, "Wohnen");
        Vocab garten = new Vocab(Long.valueOf(596),list3, "Wohnen");
        Vocab dach = new Vocab(Long.valueOf(5670),list4, "Wohnen");
        Question question = new Question(Long.valueOf(1),martin, stella, game, round, baum, haus, garten, dach);

        long requestId = 123456;
        Status requestStatus = Status.PENDING;


        //2. Act
        Request req = service.createRequest(requestId, requestStatus, requester, receiver);

        //3. Assert
        Assert.assertNotNull(req);
        Assert.assertEquals(requestId, req.getRequestId());
        Assert.assertEquals(requestStatus, req.getRequestStatus());
        Assert.assertEquals(requester, req.getRequester());
        Assert.assertEquals(receiver, req.getReceiver());
    }

}
