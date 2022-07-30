package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
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


        Translation wrongA = setAnswerOptions();
        Translation wrongB = setAnswerOptions();
        Translation wrongC = setAnswerOptions();

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
    @Transactional
    public List<Translation> getAllAnswers(Question question) {
        List<Translation> translations = new ArrayList<> ();

        try {
            translations.add(questionDao.getQuestionById(question.getQuestionId()).getRightAnswer());
            translations.add(questionDao.getQuestionById(question.getQuestionId()).getWrongA ());
            translations.add(questionDao.getQuestionById(question.getQuestionId()).getWrongB());
            translations.add(questionDao.getQuestionById(question.getQuestionId()).getWrongC());
        } catch (CustomObjectNotFoundException e) {
            throw new RuntimeException();
        }

        for (Translation t: translations) {
            Hibernate.initialize(t.getTranslations());
            Hibernate.initialize(t.getVocabs());
        }

        return translations;
    }

    @Transactional
    public List<Question> createQuestions(Game game, VocabList chosenVocabList, Round round) throws CustomOptimisticLockExceptionGame {
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
    public boolean answeredQuestion(String answer, Long questionId) throws CustomObjectNotFoundException {

        Question question = getQuestionById(questionId);

        Translation rightAnswer = question.getRightAnswer();
        List<String> translations = rightAnswer.getTranslations();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }



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

    @Transactional
    @Override
    public Question getQuestionById(Long Id) throws CustomObjectNotFoundException {
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
