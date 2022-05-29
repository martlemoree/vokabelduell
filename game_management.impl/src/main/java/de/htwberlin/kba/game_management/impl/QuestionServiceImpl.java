package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    public Question createQuestion(Long questionId, Round round, VocabList vocabList) {

        Random rand = new Random();

        Vocab vocab =  vocabList.getVocabs().get(rand.nextInt(vocabList.getVocabs().size()));
        Translation rightAnswer = vocab.getTranslations().get(rand.nextInt(vocab.getTranslations().size()));

        Translation wrongA = setAnswerOptions();
        Translation wrongB = setAnswerOptions();
        Translation wrongC = setAnswerOptions();

        return new Question(1L, round, wrongA, wrongB, wrongC, rightAnswer, vocab);
    }

    public Translation setAnswerOptions() {

        Random rand = new Random();
        VocabListService vocabListService = new VocabListServiceImpl ();

        VocabList randomVocabList = vocabListService.getVocabLists().get(rand.nextInt(vocabListService.getVocabLists().size()));
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
