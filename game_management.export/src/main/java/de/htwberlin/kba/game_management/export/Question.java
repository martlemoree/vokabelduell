package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;

public class Question {
    // Vorschlag wurden in Kempas Beispiel lediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // toString Methode überschreiben, um eine sinnvolle Frage hervorzubringen: z.B.
    // return "Was bedeutet " + Vokabel + " auf " + Fremdsprache + "?";

    private Long questionId;
    private User requester;
    private User receiver;
    private Game game;
    private Round round;
    private Vocab wrongA;
    private Vocab wrongB;
    private Vocab wrongC;
    private Vocab rightAnswer;
    private boolean correctAnswered;

    public Question(Long questionId, User requester, User receiver, Game game, Round round, Vocab wrongA, Vocab wrongB, Vocab wrongC, Vocab rightAnswer) {
        this.questionId = questionId;
        this.requester = requester;
        this.receiver = receiver;
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
