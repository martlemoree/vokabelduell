package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;

import javax.persistence.*;

@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long roundId;

    @ManyToOne
    @Column(name = "round_game")
    private Game game;

    @Column(name = "current_round")
    private int currentRound;

    @ManyToOne
    @Column(name = "vocab_list")
    private VocabList vocablist;

    public Round(Long roundId, Game game, int currentRound) {
        this.roundId = roundId;
        this.game = game;
        this.currentRound = currentRound;
    }

    public Round() {

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
