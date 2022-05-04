package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

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
}
