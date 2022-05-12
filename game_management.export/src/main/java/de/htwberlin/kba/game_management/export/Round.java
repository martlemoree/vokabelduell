package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

public class Round {

    private Long roundId;
    private Game game;
    private User requester;
    private User receiver;
    private int currentRound;
    private VocabList vocablist;

    public Round(Long roundId, Game game, User requester, User receiver, int currentRound) {
        this.roundId = roundId;
        this.game = game;
        this.requester = requester;
        this.receiver = receiver;
        this.currentRound = currentRound;
    }

    public Long getRoundId() {
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

    public VocabList getVocablist() {
        return vocablist;
    }

    public void setVocablist(VocabList vocablist) {
        this.vocablist = vocablist;
    }
}
