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
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Transactional
    public Question createQuestion(Round round, VocabList vocabList) {

        Random rand = new Random();
        /*int size = vocabList.getVocabs().size();
        int i = rand.nextInt(size);

        Vocab vocab =  vocabList.getVocabs()
                .get(i);*/

        Vocab vocab =  vocabList.getVocabs()
                .get(rand.nextInt(vocabList.getVocabs()
                        .size()));

        Translation rightAnswer = vocab.getTranslations()
                .get(rand.nextInt(vocab.getTranslations()
                        .size()));

        /*int size1 = vocab.getTranslations().size();
        int j = rand.nextInt(size1);

        Translation rightAnswer = vocab.getTranslations().get(j);*/


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

       /* for (VocabList v: vocablists) {
            List<Vocab> vocabs = v.getVocabs();

            for (Vocab vocab: vocabs) {
                Hibernate.initialize(vocab.getVocabs());
                Hibernate.initialize(vocab.getTranslations());

                List<Translation>translations = vocab.getTranslations();

                for (Translation t:translations) {
                    Hibernate.initialize(t.getVocabs());
                    Hibernate.initialize(t.getTranslations());

                    List<Vocab> vocabs2 = t.getVocabs();

                    for (Vocab vocab2: vocabs2) {
                        Hibernate.initialize(vocab2.getVocabs());
                        Hibernate.initialize(vocab2.getTranslations());
                    }
            }}}



            for (Vocab vocab: vocabs) {
                Hibernate.initialize(vocab.getVocabs());
                List<Translation>translations = vocab.getTranslations();
                for (Translation t:translations) {
                    Hibernate.initialize(t.getVocabs());
                    List<Vocab> vocabs2 = t.getVocabs();
                    for (Vocab vocab2: vocabs2) {
                        Hibernate.initialize(t.getVocabs());
                        Hibernate.initialize(t.getTranslations());
                    }
                    Hibernate.initialize(t.getTranslations());
                }
            }*/

        VocabList randomVocabList = vocablists.get(rand.nextInt(vocablists.size()));
        List<Translation> randomTranslationList = randomVocabList.getVocabs().get(rand.nextInt(randomVocabList.getVocabs().size())).getTranslations();

        /*for (Translation tr: randomTranslationList) {
            Hibernate.initialize(tr.getTranslations());
            Hibernate.initialize(tr.getVocabs());

            List<Vocab> vocabs3 = tr.getVocabs();

            for (Vocab vocab3: vocabs3) {
                Hibernate.initialize(vocab3.getVocabs());
                Hibernate.initialize(vocab3.getTranslations());
            }

        }*/

        return randomTranslationList.get(rand.nextInt(randomTranslationList.size()));
    }

    public List<Translation> getAllAnswers(List<Question> questions, int i) {
        Question question = questions.get(i);

        List<Translation> translations = new ArrayList<> ();
        translations.add(question.getRightAnswer());
        translations.add(question.getWrongA ());
        translations.add(question.getWrongB());
        translations.add(question.getWrongC());

        return translations;
    }

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

        return questions;
    }

    public List<String> giveAnswerOptionsRandom(List<Question> questions, int i) {

        Question question = questions.get(i);
        Random rand = new Random();
        List<String> answerOptions = new ArrayList<>();

        // create translations list to extract answer options randomly
        List<Translation> translations = getAllAnswers(questions, i);

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

    public boolean answeredQuestion(String answer, List<Question> questions, int i) {

        Translation rightAnswer = questions.get(i).getRightAnswer();
        List<String> translations = rightAnswer.getTranslations();

        for (String translation : translations) {
            if (answer.equals(translation)) {
                return true;
            }
        }

        return false;
    }

    public String giveVocabStringRandom(List<Question> questions, int i) {
        Vocab vocab = questions.get(i).getVocab();
        Random rand = new Random();

        int index = rand.nextInt(vocab.getVocabs().size()-1);
        return vocab.getVocabs().get(index);
    }

    @Transactional
    @Override
    public List<Question> getAllQuestions(){
        List<Question> questions = questionDao.getAllQuestions();

        for (Question q: questions) {
            Hibernate.initialize(q.getVocab().getVocabs());
            Hibernate.initialize(q.getVocab().getTranslations());

            List<Translation> translations = q.getVocab().getTranslations();
            for (Translation t: translations) {
                Hibernate.initialize(t.getTranslations());
                Hibernate.initialize(t.getVocabs());
            }
        }
        return questions;
    }


}
