package de.htwberlin.kba.game_management.export;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.htwberlin.kba.user_management.export.User;
import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="getAllGames", query="FROM Game AS games"),
        @NamedQuery(name="getAllGamesFromUser", query="FROM Game AS games WHERE games.requester.id = :userId OR games.receiver.id = :userId")
})

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "points_requester")
    private int pointsRequester;

    @Column(name = "points_receiver")
    private int pointsReceiver;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "requester_id", referencedColumnName = "user_id")
    private User requester;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "game_rounds")
    @JsonManagedReference
    private List<Round> rounds;

    @Version
    private Integer version;

    public Game(User requester, User receiver) {
        this.requester = requester;
        this.receiver = receiver;
    }

    public Game() {

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
