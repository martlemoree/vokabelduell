package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final VocabListService vocabListService;
    private final RoundDao roundDao;


    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao,  VocabListService vocabListService, RoundDao roundDao) {
        this.questionDao = questionDao;
        this.vocabListService = vocabListService;
        this.roundDao = roundDao;
    }

    @Transactional
    public Question createQuestion(Round round, VocabList vocabList) {

        Random rand = new Random();

        Vocab vocab =  vocabList.getVocabs()
                .get(rand.nextInt(vocabList.getVocabs()
                        .size()));

        Translation rightAnswer = vocab.getTranslations()
                .get(rand.nextInt(vocab.getTranslations()
                        .size()));


        Translation wrongA = setAnswerOptions(); //rightAnswer.getTranslationId());
        Translation wrongB = setAnswerOptions(); // TODO rightAnswer.getTranslationId());
        Translation wrongC = setAnswerOptions(); // TODO rightAnswer.getTranslationId());

        Question question = new Question(round, wrongA,wrongB,wrongC,rightAnswer, vocab);
        questionDao.createQuestion(question);

        return question;
    }

    public Translation setAnswerOptions() {

        Random rand = new Random();
        List<VocabList> vocablists = vocabListService.getVocabLists();


        VocabList randomVocabList = vocablists.get(rand.nextInt(vocablists.size()));
        List<Translation> randomTranslationList = randomVocabList.getVocabs().get(rand.nextInt(randomVocabList.getVocabs().size())).getTranslations();

        Translation translation = randomTranslationList.get(rand.nextInt(randomTranslationList.size()));

        // TODO hiermit sicherstellen, dass nicht die richtige Antwort als falsche antwort genommen wird
        // TODO eigtl müssten wir noch einen check einbauen dass sich bei wrongA/B/C nichts doppelt
        /*while (translation.getTranslationId() != rightId){
            translation = randomTranslationList.get(rand.nextInt(randomTranslationList.size()));
        }*/

        return translation;
    }

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    @Override
    public List<Translation> getAllAnswers(Question question) {
        List<Translation> translations = new ArrayList<> ();
        translations.add(question.getRightAnswer());
        translations.add(question.getWrongA ());
        translations.add(question.getWrongB());
        translations.add(question.getWrongC());

        return translations;
    }

    @Transactional
    public List<Question> createQuestions(Game game, VocabList chosenVocabList, Round round) {
        List<Question> questions = new ArrayList<>();
        Question question1 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question2 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        Question question3 = createQuestion(game.getRounds().get(game.getRounds().size()-1), chosenVocabList);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        // der Round hinzufügen
        round.setQuestions(questions);
        roundDao.updateRound(round);

        return questions;
    }

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    @Override
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

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    @Override
    public boolean answeredQuestion(String answer, Question question) {

        Translation rightAnswer = question.getRightAnswer();
        List<String> translations = rightAnswer.getTranslations();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }

    // Hier wird ein Object übergeben und keine Liste, da sonst der sonst der API Call nicht funktioniert hat.
    @Override
    public String giveVocabStringRandom(Question question) {
        Vocab vocab = question.getVocab();
        Random rand = new Random();

        int index = rand.nextInt(vocab.getVocabs().size()-1);
        return vocab.getVocabs().get(index);
    }

    // not tested becase the method contains only the database call
    @Transactional
    @Override
    public List<Question> getAllQuestions(){
        List<Question> questions = questionDao.getAllQuestions();

        for (Question q: questions) {
            Hibernate.initialize(q.getVocab().getVocabs());
            Hibernate.initialize(q.getVocab().getTranslations());
            Hibernate.initialize(q.getRound().getQuestions());

            List<Translation> translations = q.getVocab().getTranslations();
            for (Translation t: translations) {
                Hibernate.initialize(t.getTranslations());
                Hibernate.initialize(t.getVocabs());
            }
        }
        return questions;
    }

    // not tested becase the method contains only the database call
    @Transactional
    @Override
    public Question getQuestionById(Long Id){
        Question question =  questionDao.getQuestionById(Id);

        Hibernate.initialize(question.getVocab().getVocabs());
        Hibernate.initialize(question.getVocab().getTranslations());

        List<Translation> translations = question.getVocab().getTranslations();
        for (Translation t: translations) {
            Hibernate.initialize(t.getTranslations());
            Hibernate.initialize(t.getVocabs());
        }
        return question;

    }


}
