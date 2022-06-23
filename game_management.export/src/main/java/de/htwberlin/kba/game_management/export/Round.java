package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.vocab_management.export.VocabList;
import javax.persistence.*;
import java.util.List;

@NamedQuery(name="getAllRounds", query="FROM Round AS rounds")
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

    @ManyToOne
    @JoinColumn(name = "vocablist_id", referencedColumnName = "vocablist_id")
    private VocabList vocablist;

    // TODO: DAO NEUE FELDER:
    private boolean playedByTwo;

    // Sorry Martin, das musste ich hier schon mal einfügen weil sonst Fehlermeldung
    @OneToMany
    @Column(name = "questions")
    private List<Question> questions;

    @Version
    private Integer version;

    public Round(Game game) {
        this.roundId = roundId;
        this.game = game;
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
