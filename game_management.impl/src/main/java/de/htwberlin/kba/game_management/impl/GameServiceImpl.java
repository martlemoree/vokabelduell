package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private RoundService roundService;
    private QuestionService questionService;


    @Autowired
    public GameServiceImpl(GameDao gameDao, RoundService roundService, QuestionService questionService) {
        super();
        this.gameDao = gameDao;
        this.roundService = roundService;
        this.questionService = questionService;
    }

    @Override
    public Game createGame(User requester, User receiver) {
        Game game =  new Game (requester, receiver);
        this.gameDao.createGame(game);
        return game;
    }


    public void calculatePoints(Game game, User user, int points) {
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

    public List<Game> getGamesFromCurrentUser(User user) {
        List<Game> gamesFromUser = gameDao.getAllGamesFromUser(user.getUserId());

        for (Game g:gamesFromUser ) {
            if (g.getRounds().size() >= 6){
                gamesFromUser.remove(g);
            }
        }
        return gamesFromUser;
    }

    public List<Question> giveQuestions(Game game, User currentUser, VocabList vocabList) {

        List<Round> rounds = game.getRounds();

        // if game has not just been created and the last round was not played by both players
        // the last existing round of the game is played
        if (!rounds.isEmpty() & !rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
            Round round = rounds.get(game.getRounds().size()-1);

            round.setPlayedByTwo(true);
            return round.getQuestions();
        }

        // if game has just been started OR the last round of the game was played by both players
        // (happens in the if clause above)
        // new Round must be started
        Round round = roundService.startNewRound(game);

        // generate Questions
        return questionService.createQuestions(game, vocabList, round);
    }
}