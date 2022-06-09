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

    private List<String> answerOptions;
    private List<Translation> translations;

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
        VocabListService vocabListService = new VocabListServiceImpl();

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

    public List<Question> createQuestions(Game game, VocabList chosenVocabList) {
        List<Question> questions = new ArrayList<>();
        Question question1 = createQuestion(1L, game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question2 = createQuestion(1L, game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question3 = createQuestion(1L, game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }

    public List<String> giveAnswerOptionsRandom(Question question) {

        answerOptions.clear();
        translations.clear();

        Random rand = new Random();

        // create translations list to extract answer options randomly
        translations = getAllAnswers(question);

        // get Random Translation (if various possibilities)
        for (int i = 0; i<5; i++) {
            int index = rand.nextInt(translations.size()-1);
            createAnswerOptions(index);
        }

        return answerOptions;
    }

    public void createAnswerOptions(int index) {

        Random rand = new Random();

        List<String> translationStrings1 = translations.get(index).getTranslations();

        // get Random Translation String (if various possibilities)
        int translationStringsindex1 = rand.nextInt(translationStrings1.size());
        // add to answerOptionsList of Strings and remove entry from translations list
        answerOptions.add(translationStrings1.get(translationStringsindex1));
        translations.remove(index);
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
