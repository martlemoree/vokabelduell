package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import de.htwberlin.kba.vocab_management.impl.VocabListDao;
import de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;
    private VocabListDao vocabListDao;
    private VocabListService vocabListService;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao, VocabListDao vocabListDao, VocabListService vocabListService) {
        this.questionDao = questionDao;
        this.vocabListDao = vocabListDao;
        this.vocabListService = vocabListService;
    }

    public Question createQuestion(Round round, VocabList vocabList) {

        Random rand = new Random();

        Vocab vocab =  vocabList.getVocabs().get(rand.nextInt(vocabList.getVocabs().size()));
        Translation rightAnswer = vocab.getTranslations().get(rand.nextInt(vocab.getTranslations().size()));

        Translation wrongA = setAnswerOptions();
        Translation wrongB = setAnswerOptions();
        Translation wrongC = setAnswerOptions();

        return new Question(round, wrongA,
                wrongB,
                wrongC,
                rightAnswer, vocab);
    }

    public Translation setAnswerOptions() {

        Random rand = new Random();
        List<VocabList> vocablists = vocabListService.getVocabLists();

        VocabList randomVocabList = vocablists.get(rand.nextInt(vocablists.size()));
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

    public List<Question> createQuestions(Game game, VocabList chosenVocabList) {
        List<Question> questions = new ArrayList<>();
        Question question1 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question2 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question3 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }

    public List<String> giveAnswerOptionsRandom(Question question) {

        Random rand = new Random();
        List<String> answerOptions = new ArrayList<>();

        // create translations list to extract answer options randomly
        List<Translation> translations = getAllAnswers(question);

        // get Random Translation (if various possibilities)
        int index1 = rand.nextInt(translations.size()-1);
        List<String> translationStrings1 = translations.get(index1).getTranslations();

        // get Random Translation String (if various possibilities)
        int translationStringsindex1 = rand.nextInt(translationStrings1.size());

        // add to answerOptionsList of Strings and remove entry from translations list
        answerOptions.add(translationStrings1.get(translationStringsindex1));
        translations.remove(index1);

        // das ganze 4 Mal, noch keine einfache Lösung gefunden das auszugliedern
        int index2 = rand.nextInt(translations.size()-1);
        List<String> translationStrings2 = translations.get(index2).getTranslations();
        int translationStringsindex2 = rand.nextInt(translationStrings2.size());

        answerOptions.add(translationStrings2.get(translationStringsindex2));
        translations.remove(index2);

        int index3 = rand.nextInt(translations.size()-1);
        List<String> translationStrings3 = translations.get(index3).getTranslations();
        int translationStringsindex3 = rand.nextInt(translationStrings3.size());
        answerOptions.add(translationStrings3.get(translationStringsindex3));
        translations.remove(index3);

        List<String> translationStrings4 = translations.get(0).getTranslations();
        int translationStringsindex4 = rand.nextInt(translationStrings4.size());

        answerOptions.add(translationStrings4.get(translationStringsindex4));
        translations.remove(0);

        return answerOptions;
    }

    public boolean answeredQuestion(String answer, Translation rightAnswer) {

        List<String> translations = rightAnswer.getTranslations();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }
}
