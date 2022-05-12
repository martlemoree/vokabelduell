package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public boolean answerQuestion(String answer, Vocab rightAnswer, User requester, User receiver, Question question) {
        return true;
    }

    @Override
    public Question createQuestion(Long questionId, User requester, User receiver, Game game, Round round) {
        return null;
    }

    @Override
    public void setAnswerOptions(Question question) {

    }

    @Override
    public List<Vocab> getAllAnswers() {
        return null;
    }

}
