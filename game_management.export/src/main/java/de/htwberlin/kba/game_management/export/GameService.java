package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

public interface GameService {

    /**
     * Contains the game logic that one game consists of 6 rounds
     */
    public void playGame();

    /**
     * creates a new Game.
     * @param gameId is the unique identifier of the game.
     * @param pointsRequester is the current score of the user who sent the request.
     * @param pointsReceiver is the current score of the user who received the request.
     * @param requester is the user who sent the request for the game.
     * @param receiver is the user who received and accepted the game request.
     * @return a new game
     */
    public Game createGame(Long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound);

    /**
     * the user chooses a Vocablist for the current round. The questions will be created from this vocablist
     * @return the vocab
     */
    public VocabList chooseVocabList();

    Game createGame(long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound);
}
