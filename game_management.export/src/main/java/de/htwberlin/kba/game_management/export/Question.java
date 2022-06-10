package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    // Vorschlag wurden in Kempas Beispiel lediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // toString Methode überschreiben, um eine sinnvolle Frage hervorzubringen: z.B.
    // return "Was bedeutet " + Vokabel + " auf " + Fremdsprache + "?";

    //TODO (F) Question.answerQuestion evtl. nach GameService refactoren

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "round_id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "translation_wrong_a", referencedColumnName = "translation_id")
    private Translation wrongA;

    @ManyToOne
    @JoinColumn(name = "translation_wrong_b", referencedColumnName = "translation_id")
    private Translation wrongB;

    @ManyToOne
    @JoinColumn(name = "translation_wrong_c", referencedColumnName = "translation_id")
    private Translation wrongC;

    @ManyToOne
    @JoinColumn(name = "translation_right", referencedColumnName = "translation_id")
    private Translation rightAnswer;

    @ManyToOne
    @JoinColumn(name = "question_vocab", referencedColumnName = "vocab_id")
    private Vocab vocab;

    @Column(name = "correct_answered_requester")
    private boolean correctAnsweredRequester;

    @Column(name = "correct_answered_receiver")
    private boolean correctAnsweredReceiver;

    public Question(Long questionId, Round round, Translation wrongA, Translation wrongB, Translation wrongC, Translation rightAnswer, Vocab vocab) {
        this.questionId = questionId;
        this.round = round;
        this.wrongA = wrongA;
        this.wrongB = wrongB;
        this.wrongC = wrongC;
        this.rightAnswer = rightAnswer;
        this.vocab = vocab;
    }

    public Question() {

    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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
