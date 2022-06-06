package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.vocab_management.export.VocabList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long roundId;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    // TODO: DAO Das Feld kann weg!!!
    @Column(name = "current_round")
    private int currentRound;

    @ManyToOne
    @JoinColumn(name = "vocablist_id", referencedColumnName = "vocablist_id")
    private VocabList vocablist;


    // TODO: DAO NEUE FELDER:
    private boolean playedByTwo;

    // Sorry Martin, das musste ich hier schon mal einfügen weil sonst Fehlermeldung
    @OneToMany
    @Column(name = "questions")
    private List<Question> questions;

    public Round(Long roundId, Game game, int currentRound) {
        this.roundId = roundId;
        this.game = game;
        this.currentRound = currentRound;
        playedByTwo = false;
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

    public boolean getisPlayedByTwo() {
        return playedByTwo;
    }

    public void setPlayedByTwo(boolean playedByTwo) {
        this.playedByTwo = playedByTwo;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
