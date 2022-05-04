package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

public class Game {
    //wdkjnwdlwed
    //sdkcjnskjd
    private Long gameId;
    private int pointsRequester;
    private int pointsReceiver;
    private User requester;
    private User receiver;
    private int currentRound;

    public Game(Long gameId, int pointsRequester, int pointsReceiver, User requester, User receiver, int currentRound) {
        this.gameId = gameId;
        this.pointsRequester = pointsRequester;
        this.pointsReceiver = pointsReceiver;
        this.requester = requester;
        this.receiver = receiver;
        this.currentRound = currentRound;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
