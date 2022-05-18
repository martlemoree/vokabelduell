package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;


public class GameServiceImpl implements GameService {

    @Override
    public Game createGame(Long gameId, User requester, User receiver) {

        Game game = new Game(gameId, requester, receiver);
        return game;
    }

    /**
     * Contains the game logic that one game consists of 6 rounds
     * @param game the game that should be played.
     */
    public void playGame(Game game, User requester, User receiver) {

        for (int i = 1; i == 6; i++) {
            /*
            Jedes Mal wird eine neue Runde erstellt
             */
            Round round = new Round(1L, game, requester, receiver, i);

            // richtige/falsche Antwort usw.
            // Punkte berechnen, passiert aber auch in der Runde?


            // Gewinner + Loser gibt es nicht, es gibt ja auch keine statistik ._.
            // List<VocabList> getRandomVocablists() Vocablist
            // VocabList chooseVocablist(List<VocabList> randomVocabLists)
            // wie nehmen wir user-eingaben entgegen aus der konsole?
            // create Question

        }
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