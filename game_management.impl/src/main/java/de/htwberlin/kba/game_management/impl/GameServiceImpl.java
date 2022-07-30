package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
import de.htwberlin.kba.vocab_management.impl.TranslationServiceImpl;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private RoundService roundService;
    private QuestionService questionService;

    private UserService userService;
    private VocabListService vocabListService;

    private TranslationService translationService;


    @Autowired
    public GameServiceImpl(GameDao gameDao, RoundService roundService, QuestionService questionService, UserService userService, VocabListService vocabListService, TranslationService translationService) {
        super();
        this.gameDao = gameDao;
        this.roundService = roundService;
        this.questionService = questionService;
        this.userService = userService;
        this.vocabListService = vocabListService;
        this.translationService = translationService;
    }


    @Override
    @Transactional
    public Long createGame(User requester, User receiver) {
        Game game =  new Game (requester, receiver);
        this.gameDao.createGame(game);
        return game.getGameId();
    }

    @Transactional
    public void calculatePoints(Game game, User user, int points) throws CustomOptimisticLockExceptionGame {
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
    public void calculatePoints(Long gameId, String userName, int points) throws UserNotFoundException, CustomOptimisticLockExceptionGame, CustomObjectNotFoundException {
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

    public List<List<String>> giveQuestions(Long gameId, String userName, Long vocabListId) throws CustomOptimisticLockExceptionGame, CustomObjectNotFoundException, VocabListObjectNotFoundException {

        Game game = getGamebyId(gameId);
        List<Round> rounds = game.getRounds();
        List<Question> questions = null;
        VocabList vocabList = vocabListService.getVocabListById(vocabListId);

        // if game has not just been created and the last round was not played by both players
        // the last existing round of the game is played
        if (rounds.size() != 0) {
            if (!rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
                Round round = rounds.get(game.getRounds().size()-1);

                round.setPlayedByTwo(true);
                questions = round.getQuestions();
            } else if (rounds.get(rounds.size() - 1).getisPlayedByTwo()) {
                Round round = roundService.startNewRound(game);

                // generate Questions
                questions = questionService.createQuestions(game, vocabList, round);
            }
        } else if (rounds==null || rounds.isEmpty() || rounds.size() == 0) {
            // if game has just been started (and contains no rounds) OR the last round of the game was played by both players
            // (happens in the if clause above)
            // new Round must be started
            Round round = roundService.startNewRound(game);

            // generate Questions
            questions = questionService.createQuestions(game, vocabList, round);

        }

        List<String> answerOptions1 = giveAnswerOptionsRandom(questions.get(0).getQuestionId());
        String vocabString1 = giveVocabStringRandom(questions.get(0).getQuestionId());
        String question1answer1 =  answerOptions1.get(0);
        String question1answer2 =  answerOptions1.get(1);
        String question1answer3 =  answerOptions1.get(2);
        String question1answer4 =  answerOptions1.get(3);
        String questionId1 =  String.valueOf(questions.get(0).getQuestionId());
        List<String> question1 = new ArrayList<>();
        question1.add(vocabString1);
        question1.add(question1answer1);
        question1.add(question1answer2);
        question1.add(question1answer3);
        question1.add(question1answer4);
        question1.add(questionId1);

        List<String> answerOptions2 = giveAnswerOptionsRandom(questions.get(1).getQuestionId());
        String vocabString2 = giveVocabStringRandom(questions.get(1).getQuestionId());
        String question2answer1 =  answerOptions2.get(0);
        String question2answer2 =  answerOptions2.get(1);
        String question2answer3 =  answerOptions2.get(2);
        String question2answer4 =  answerOptions2.get(3);
        String questionId2 =  String.valueOf(questions.get(1).getQuestionId());
        List<String> question2 = new ArrayList<>();
        question2.add(vocabString2);
        question2.add(question2answer1);
        question2.add(question2answer2);
        question2.add(question2answer3);
        question2.add(question2answer4);
        question2.add(questionId2);

        List<String> answerOptions3 = giveAnswerOptionsRandom(questions.get(2).getQuestionId());
        String vocabString3 = giveVocabStringRandom(questions.get(2).getQuestionId());
        String question3answer1 =  answerOptions3.get(0);
        String question3answer2 =  answerOptions3.get(1);
        String question3answer3 =  answerOptions3.get(2);
        String question3answer4 =  answerOptions3.get(3);
        String questionId3 =  String.valueOf(questions.get(2).getQuestionId());
        List<String> question3 = new ArrayList<>();
        question3.add(vocabString3);
        question3.add(question3answer1);
        question3.add(question3answer2);
        question3.add(question3answer3);
        question3.add(question3answer4);
        question3.add(questionId3);

        List<List<String>> questionsStringList = new ArrayList<>();
        questionsStringList.add(question1);
        questionsStringList.add(question2);
        questionsStringList.add(question3);

        return null;
    }

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    public List<String> giveAnswerOptionsRandom(Long questionId) throws CustomObjectNotFoundException {
        Random rand = new Random();
        List<String> answerOptions = new ArrayList<>();
        Question question = questionService.getQuestionById(questionId);

        // create translations list to extract answer options randomly
        List<Translation> translations = questionService.getAllAnswers(question);



        // get Random Translation (if various possibilities)
        int index1 = rand.nextInt(translations.size()-1);

        List<String> translationStrings1 = translationService.getAllTranslationStrings(translations.get(index1).getTranslationId());
                //translations.get(index1).getTranslations();

        // get Random Translation String (if various possibilities)
        int translationStringsindex1 = 0;
        if (translationStrings1.size() > 1) {
            translationStringsindex1 = rand.nextInt(translationStrings1.size()-1);
        }

        // add to answerOptionsList of Strings and remove entry from translations list
        answerOptions.add(translationStrings1.get(translationStringsindex1));
        translations.remove(index1);





        // das ganze 4 Mal, noch keine einfache Lösung gefunden das auszugliedern
        int index2 = rand.nextInt(translations.size()-1);
        List<String> translationStrings2 = translationService.getAllTranslationStrings(translations.get(index2).getTranslationId());

        int translationStringsindex2 = 0;
        if (translationStrings1.size() > 1) {
            translationStringsindex2 = rand.nextInt(translationStrings2.size()-1);
        }

        answerOptions.add(translationStrings2.get(translationStringsindex2));
        translations.remove(index2);




        int index3 = rand.nextInt(translations.size()-1);
        List<String> translationStrings3 = translationService.getAllTranslationStrings(translations.get(index3).getTranslationId());

        int translationStringsindex3 = 0;
        if (translationStrings1.size() > 1) {
            translationStringsindex3 = rand.nextInt(translationStrings3.size()-1);

        }
        answerOptions.add(translationStrings3.get(translationStringsindex3));
        translations.remove(index3);




        List<String> translationStrings4 = translationService.getAllTranslationStrings(translations.get(0).getTranslationId());
        int translationStringsindex4 = 0;
        if (translationStrings1.size() > 1) {
            translationStringsindex4 = rand.nextInt(translationStrings4.size()-1);
        }

        answerOptions.add(translationStrings4.get(translationStringsindex4));
        translations.remove(0);

        return answerOptions;
    }

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    @Override
    public String giveVocabStringRandom(Long questionid) throws CustomObjectNotFoundException {
        Question question = questionService.getQuestionById(questionid);
        Vocab vocab = question.getVocab();
        Random rand = new Random();

        int index = 0;
        if (vocab.getVocabs().size() > 1) {
            index = rand.nextInt(vocab.getVocabs().size()-1);

        }
        return vocab.getVocabs().get(index);
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
