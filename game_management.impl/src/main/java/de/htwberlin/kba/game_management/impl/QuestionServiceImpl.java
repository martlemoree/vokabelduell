package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.htwberlin.kba.vocab_management.export.VocabList.vocablists;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public boolean answerQuestion(String answer, Translation rightAnswer, User requester, User receiver, Question question) {

        List<String> translations = rightAnswer.getTranslations ();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }

    public Question createQuestion(Long questionId, User requester, User receiver, Game game, Round round, VocabList vocabList) {

        Random rand = new Random();

        Vocab vocab =  vocabList.getVocabs().get(rand.nextInt(vocabList.getVocabs().size()));
        Translation rightAnswer = vocab.getTranslations().get(rand.nextInt(vocab.getTranslations().size()));

        Translation wrongA = setAnswerOptions();
        Translation wrongB = setAnswerOptions();
        Translation wrongC = setAnswerOptions();

        return new Question(1L,requester, receiver, game, round, wrongA, wrongB, wrongC, rightAnswer, vocab);
    }

    public Translation setAnswerOptions() {

        Random rand = new Random();

        VocabList randomVocabList = VocabList.getVocablists().get(rand.nextInt(VocabList.getVocablists().size()));
        List<Translation> randomTranslationList = randomVocabList.getVocabs().get(rand.nextInt(randomVocabList.getVocabs().size())).getTranslations();

        return randomTranslationList.get(rand.nextInt(randomTranslationList.size()));
    }

    public List<Translation> getAllAnswers(Question question) {
        List<Translation> translations = new ArrayList<> ();
        translations.add(question.getRightAnswer());
        translations.add(question.getWrongA ());
        translations.add(question.getWrongB());
        translations.add(question.getWrongC());

        return translations;
    }

}
