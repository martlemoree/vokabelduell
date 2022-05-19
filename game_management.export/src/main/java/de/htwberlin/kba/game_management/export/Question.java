package de.htwberlin.kba.game_management.export;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;

public class Question {
    // Vorschlag wurden in Kempas Beispiel lediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // toString Methode überschreiben, um eine sinnvolle Frage hervorzubringen: z.B.
    // return "Was bedeutet " + Vokabel + " auf " + Fremdsprache + "?";

    //todo (F) Question.answerQuestion evtl. nach GameService refactoren
    private Long questionId;
    private User requester;
    private User receiver;
    private Game game;
    private Round round;
    private Translation wrongA;
    private Translation wrongB;
    private Translation wrongC;
    private Translation rightAnswer;
    private Vocab vocab;
    private boolean correctAnsweredRequester;
    private boolean correctAnsweredReceiver;

    public Question(Long questionId, User requester, User receiver, Game game, Round round, Translation wrongA, Translation wrongB, Translation wrongC, Translation rightAnswer, Vocab vocab) {
        this.questionId = questionId;
        this.requester = requester;
        this.receiver = receiver;
        this.game = game;
        this.round = round;
        this.wrongA = wrongA;
        this.wrongB = wrongB;
        this.wrongC = wrongC;
        this.rightAnswer = rightAnswer;
        this.vocab = vocab;
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

    public Translation getWrongA() {
        return wrongA;
    }

    public void setWrongA(Translation wrongA) {
        this.wrongA = wrongA;
    }

    public Translation getWrongB() {
        return wrongB;
    }

    public void setWrongB(Translation wrongB) {
        this.wrongB = wrongB;
    }

    public Translation getWrongC() {
        return wrongC;
    }

    public void setWrongC(Translation wrongC) {
        this.wrongC = wrongC;
    }

    public Translation getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Translation rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isCorrectAnsweredRequester() {
        return correctAnsweredRequester;
    }

    public void setCorrectAnsweredRequester(boolean correctAnsweredRequester) {
        this.correctAnsweredRequester = correctAnsweredRequester;
    }

    public boolean isCorrectAnsweredReceiver() {
        return correctAnsweredReceiver;
    }

    public void setCorrectAnsweredReceiver(boolean correctAnsweredReceiver) {
        this.correctAnsweredReceiver = correctAnsweredReceiver;
    }

    public Vocab getVocab() {
        return vocab;
    }

    public void setVocab(Vocab vocab) {
        this.vocab = vocab;
    }
}
