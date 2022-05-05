package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Vocab;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public boolean answerQuestion(String answer, Vocab rightAnswer, User requester, User receiver) {
        return true;
    }

}
