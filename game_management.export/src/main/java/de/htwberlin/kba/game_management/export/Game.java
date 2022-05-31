package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @Column(name = "game_requester")
    private User requester;

    @OneToOne
    @Column(name = "game_receiver")
    private User receiver;

    @OneToMany
    @Column(name = "game_rounds")
    private List<Round> rounds;

    public Game(Long gameId,  User requester, User receiver) {
        this.gameId = gameId;
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
