package de.htwberlin.kba.game_management.export;

public class Game {

    private Long GameId;
    private int PointsRequester;
    private int PointsReceiver;
    private Long Requester;
    private Long Receiver;
    private int CurrentRound;

    public Long getGameId() {
        return GameId;
    }

    public void setGameId(Long gameId) {
        GameId = gameId;
    }

    public int getPointsRequester() {
        return PointsRequester;
    }

    public void setPointsRequester(int pointsRequester) {
        PointsRequester = pointsRequester;
    }

    public int getPointsReceiver() {
        return PointsReceiver;
    }

    public void setPointsReceiver(int pointsReceiver) {
        PointsReceiver = pointsReceiver;
    }

    public Long getRequester() {
        return Requester;
    }

    public void setRequester(Long requester) {
        Requester = requester;
    }

    public Long getReceiver() {
        return Receiver;
    }

    public void setReceiver(Long receiver) {
        Receiver = receiver;
    }

    public int getCurrentRound() {
        return CurrentRound;
    }

    public void setCurrentRound(int currentRound) {
        CurrentRound = currentRound;
    }
}
