package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;

public class Question {
    // Vorschlag wurden in Kempas Beispiel lediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // toString Methode überschreiben, um eine sinnvolle Frage hervorzubringen: z.B.
    // return "Was bedeutet " + Vokabel + " auf " + Fremdsprache + "?";

    private Long questionId;
    private User user;
    private Game game;
    private Round round;
    private Vocab wrongA;
    private Vocab wrongB;
    private Vocab wrongC;
    private Vocab rightAnswer;
    private boolean correctAnswered;

    public Question(Long questionId, User user, Game game, Round round, Vocab wrongA, Vocab wrongB, Vocab wrongC, Vocab rightAnswer) {
        this.questionId = questionId;
        this.user = user;
        this.game = game;
        this.round = round;
        this.wrongA = wrongA;
        this.wrongB = wrongB;
        this.wrongC = wrongC;
        this.rightAnswer = rightAnswer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Vocab getWrongA() {
        return wrongA;
    }

    public void setWrongA(Vocab wrongA) {
        this.wrongA = wrongA;
    }

    public Vocab getWrongB() {
        return wrongB;
    }

    public void setWrongB(Vocab wrongB) {
        this.wrongB = wrongB;
    }

    public Vocab getWrongC() {
        return wrongC;
    }

    public void setWrongC(Vocab wrongC) {
        this.wrongC = wrongC;
    }

    public Vocab getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Vocab rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isCorrectAnswered() {
        return correctAnswered;
    }

    public void setCorrectAnswered(boolean correctAnswered) {
        this.correctAnswered = correctAnswered;
    }
}
