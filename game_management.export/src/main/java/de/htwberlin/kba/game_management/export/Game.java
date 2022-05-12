package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

import java.util.List;

public class Game {
    private Long gameId;
    private int pointsRequester;
    private int pointsReceiver;
    private User requester;
    private User receiver;
    private List<Round> rounds;

    public Game(Long gameId,  User requester, User receiver) {
        this.gameId = gameId;
        this.requester = requester;
        this.receiver = receiver;
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


    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

}
