package org.example;

import static org.junit.Assert.assertTrue;

import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.game_management.impl.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.impl.UserDao;
import de.htwberlin.kba.user_management.impl.UserDaoImpl;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vocab_management.impl.TranslationDaoImpl;
import de.htwberlin.kba.vocab_management.impl.VocabDaoImpl;
import de.htwberlin.kba.vocab_management.impl.VocabListDaoImpl;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    public AppTest() {
    }

    @Test
    public void shouldAnswerWithTrue()
    {

        //Entity Manager Mocken
        GameDao gameDao = new GameDaoImpl();
        RoundService roundService = new RoundServiceImpl(new RoundDaoImpl());
        QuestionService questionService = new QuestionServiceImpl(new QuestionDaoImpl(), new VocabListDaoImpl(), new VocabListServiceImpl(new VocabListDaoImpl(), new TranslationDaoImpl(), new VocabDaoImpl()));
        GameServiceImpl service = new GameServiceImpl(gameDao, roundService, questionService);
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        User user = userService.getUserByUserName("AntjeWinner");

        System.out.println(service.getGamesFromCurrentUser(user));

        assertTrue( true );
    }




}
