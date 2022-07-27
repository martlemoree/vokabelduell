package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private RoundService roundService;
    private QuestionService questionService;

    private UserService userService;


    @Autowired
    public GameServiceImpl(GameDao gameDao, RoundService roundService, QuestionService questionService, UserService userService) {
        super();
        this.gameDao = gameDao;
        this.roundService = roundService;
        this.questionService = questionService;
        this.userService = userService;
    }


    @Override
    @Transactional
    public Game createGame(User requester, User receiver) {
        Game game =  new Game (requester, receiver);
        this.gameDao.createGame(game);
        return game;
    }

    @Transactional
    @Override
    public void calculatePoints(Game game, User user, int points) throws CustomLockException {
        if (user.equals(game.getReceiver ())) {
            int sum = game.getPointsReceiver()+points;
            game.setPointsReceiver(sum);
        }
        if (user.equals(game.getRequester ())) {
            int sum = game.getPointsReceiver()+points;
            game.setPointsRequester(sum);
        }
        gameDao.updateGame(game);
    }

    @Transactional
    @Override
    public void calculatePoints(Long gameId, String userName, int points) throws UserNotFoundException, CustomLockException, CustomObjectNotFoundException {
        User user = userService.getUserByUserName(userName);
        Game game = this.getGamebyId(Long.valueOf(gameId));
        if (user.equals(game.getReceiver ())) {
            int sum = game.getPointsReceiver()+points;
            game.setPointsReceiver(sum);
        }
        if (user.equals(game.getRequester ())) {
            int sum = game.getPointsRequester()+points;
            game.setPointsRequester(sum);
        }
        gameDao.updateGame(game);
    }

    @Transactional
    @Override
    public List<Game> getGamesFromCurrentUser(String userName) throws UserNotFoundException {
        User user = userService.getUserByUserName(userName);
        List<Game> gamesFromUser = gameDao.getAllGamesFromUser(user);

        for (Game g:gamesFromUser ) {
            if (g.getRounds().size() >= 6) {
                gamesFromUser.remove(g);
            }
        }
        return gamesFromUser;
    }

    public List<Question> giveQuestions(Game game, User currentUser, VocabList vocabList) throws CustomLockException {

        List<Round> rounds = game.getRounds();

        // if game has not just been created and the last round was not played by both players
        // the last existing round of the game is played
        if (!(rounds==null)) {
            if (!rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
                Round round = rounds.get(game.getRounds().size()-1);

                round.setPlayedByTwo(true);
                return round.getQuestions();
            }
        }

        // if game has just been started (and contains no rounds) OR the last round of the game was played by both players
        // (happens in the if clause above)
        // new Round must be started
        Round round = roundService.startNewRound(game);

        // generate Questions
        return questionService.createQuestions(game, vocabList, round);
    }

    @Override
    @Transactional
    public Game getGamebyId(Long gameId) throws CustomObjectNotFoundException {
        Game game = gameDao.getGameById(gameId);
        Hibernate.initialize(game.getRounds());
        return game;
    }

    @Override
    @Transactional
    public List<Game> getALlGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game g: games) {
            Hibernate.initialize(g.getRounds());
            List<Round> rounds = g.getRounds();
            for (Round r:rounds) {
                Hibernate.initialize(r.getQuestions());
                List<Question> questions = r.getQuestions();
                for (Question q: questions) {
                    Hibernate.initialize(q.getVocab().getVocabs());
                    Hibernate.initialize(q.getVocab().getTranslations());

                }
            }
        }
        return games;
    }
}
