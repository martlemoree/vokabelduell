package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {

    @Override
    public boolean answerQuestion(String answer, Translation rightAnswer) {

        List<String> translations = rightAnswer.getTranslations ();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Game createGame(Long gameId, User requester, User receiver) {

        return new Game (gameId, requester, receiver);

    }

    public void playGame(Game game, User requester, User receiver) {

        // TODO Controller von der Oberfläche (Interaktion mit dem Benutzer)
        // Eine View, eine Controller-Klasse in eigener Oberflächen Projekt
        // anlegen von dem Spiel, aktuelle Frage bekommen zur Ausgabe, Antworten verbuchen, Am Ende Auswertung
        // Brauchen Round/Question usw. wirklich Interfaces?

        VocabListService vocabListService = new VocabListServiceImpl ();

        RoundService roundService = new RoundServiceImpl ();
        QuestionService questionService = new QuestionServiceImpl ();

        for (int i = 1; i < 7; i++) {
            //            Jedes Mal wird eine neue Runde erstellt

            Round round = new Round(1L, game, i);

            // get 3 random vocablists to choose from
            List<VocabList> randomVocabLists = vocabListService.getRandomVocablists();

            // user chooses vocablist for round
            VocabList chosenVocabList = roundService.chooseVocablist(randomVocabLists);

            // generate Question
            Question question = questionService.createQuestion(1L, round, chosenVocabList);

            // user answers question (where?)
            // TODO: Nutzereingabe
            String answer = "Wie und wo nehmen wir die Nutzereingabe entgegen? Am besten eigene Methode";
            boolean rightOrWrong = this.answerQuestion(answer, question.getRightAnswer ());

            // Punkte berechnen, passiert aber auch in der Runde?
            int points;
            if (rightOrWrong) {
                points = 500;
            } else {
                points = -200;
            }

            // Wie machen wir das hier mit dem User?
                    calculatePoints(game, new User( 1L,"AntjeWinner","richtigGutesPassword"), points);

        }

        // Dem User das Ende des Games zu verstehen geben, evtl. inkl. Punktestand
    }
    public void calculatePoints(Game game, User user, int points) {
        if (user.equals(game.getReceiver ())) {
            game.setPointsReceiver (points);
        }
        if (user.equals(game.getRequester ())) {
            game.setPointsRequester (points);
        }
    }


}