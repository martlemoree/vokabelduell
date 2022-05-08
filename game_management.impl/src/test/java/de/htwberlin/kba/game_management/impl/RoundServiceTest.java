package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;


public class RoundServiceTest {

    private RoundService service;

    @Before
    public void setUp() {
        this.service = new RoundServiceImpl();
    }

    @DisplayName("checks whether a Round is created correctly.")
    @Test
    public void testCreateRoundTest() {

        //1. Arrange
        Long roundId = 12345L;
        User requester = new User(234567L, "MartinTheBrain", "lol123");
        User receiver = new User(234568L, "stellomello", "123lol");
        Game game = new Game(34567L, 0, 0, requester, receiver);


        //2. Act
        Round round = service.createRound(roundId, game, requester, receiver, 1 );

        //3. Assert
        Assert.assertNotNull(round);
        Assert.assertEquals(roundId, round.getRoundId());
        Assert.assertEquals(1, round.getCurrentRound());
        Assert.assertEquals(requester, round.getRequester());
        Assert.assertEquals(receiver, round.getReceiver());

    }

    @DisplayName("checks whether a Round is created correctly.")
    @Test
    public void chooseVocablist() {

        //1. Arrange
        Long roundId = 12345L;
        User requester = new User(234567L, "MartinTheBrain", "lol123");
        User receiver = new User(234568L, "stellomello", "123lol");
        Game game = new Game(34567L, 0, 0, requester, receiver);

        VocabList vlist1 = new VocabList(1L, "Unit 1", "Food", "Englisch", null);
        VocabList vlist2 = new VocabList(2L, "Unit 1", "Drink", "Englisch", null);
        VocabList vlist3 = new VocabList(3L, "Unit 1", "verbs", "Englisch", null);
        VocabList vlist4 = new VocabList(4L, "Unit 1", "animals", "Englisch", null);

        List<VocabList> vlists = new ArrayList<>();
        vlists.add(vlist1);
        vlists.add(vlist2);
        vlists.add(vlist3);
        vlists.add(vlist4);


        //2. Act
        Round round = service.createRound(roundId, game, requester, receiver, 1 );
        VocabList chosenVocablist = service.chooseVocablist(vlists);

        //3. Assert
        Assert.assertNotNull(chosenVocablist);
        Assert.assertTrue(vlists.contains(chosenVocablist));

    }

}
