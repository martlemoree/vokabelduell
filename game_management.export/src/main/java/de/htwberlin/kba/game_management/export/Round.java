package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.user_management.export.User;

public class Round {

    private long roundId;
    private Game game;
    private int pointsRequester;
    private int pointsReceiver;
    private User requester;
    private User receiver;
    private int currentRound;

    public Round(long roundId, Game game, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound) {
        this.roundId = roundId;
        this.game = game;
        this.pointsRequester = pointsRequester;
        this.pointsReceiver = pointsReceiver;
        this.requester = requester;
        this.receiver = receiver;
        this.currentRound = currentRound;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPointsRequester() {
        return pointsRequester;
    }

    public void setPointsRequester(int pointsRequester) {
        this.pointsRequester = pointsRequester;
    }

    public int getPointsReceiver() {
        return pointsReceiver;
    }

    public void setPointsReceiver(int pointsReceiver) {
        this.pointsReceiver = pointsReceiver;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }
}
