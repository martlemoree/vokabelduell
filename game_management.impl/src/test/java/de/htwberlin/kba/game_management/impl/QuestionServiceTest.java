package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Spy // object is partially mocked. the real methods are being called
    @InjectMocks
    private QuestionServiceImpl questionService;
    @Mock
    private VocabListService mockVocabListService;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private  RoundDao roundDao;
    private Round round;
    private VocabList vocabList;
    private Translation translation;
    private Translation translation2;
    private Translation translation3;
    private Translation translation4;
    private Vocab vocab;
    private List<Question> questions;
    private Question question;
    private List<Translation> translations = new ArrayList<>();
    private List<VocabList> vocabLists = new ArrayList<>();
    private String translationString;
    private Game game;

    @Before
    public void setUp(){
        User requester = new User("Peter", "Test");
        User receiver = new User("AuchPeter", "Test123");
        game = new Game(requester, receiver);
        round = new Round(game);
        List<String> vocabStrings = new ArrayList<>();
        vocabStrings.add("Vocab");
        vocabStrings.add("Vocab2");
        translationString = "Translation";
        List<String> translationStrings = Arrays.asList(translationString);
        translation = new Translation(translationStrings);
        translations.add(translation);

        List<String> translationStrings2 = Arrays.asList("Translation2");
        translation2 = new Translation(translationStrings2);
        List<String> translationStrings3 = Arrays.asList("Translation3");
        translation3 = new Translation(translationStrings3);
        List<String> translationStrings4 = Arrays.asList("Translation4");
        translation4 = new Translation(translationStrings4);

        translations.add(translation2);
        translations.add(translation3);
        translations.add(translation4);

        vocab = new Vocab(vocabStrings, translations);
        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(vocab);

        translation.setVocabs(vocabs);
        vocabList = new VocabList("Category", "Name","Language", vocabs);

        vocabLists.add(vocabList);

        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        game.setRounds(rounds);

        question = new Question(round, translation, translation2, translation3, translation4, vocab);
        questions = new ArrayList<>();
        questions.add(question);

    }

    // Translation setAnswerOptions();
    @Test
    @DisplayName("method setAnswerOptions gives something back")
    public void testSetAnswerOptions() {
        // 1. Arrange
        // s. setup

        // 2. Act
        //  Mockito.doNothing().when(questionDao).createQuestion(Mockito.any(Question.class));
        //  Mockito.doNothing().when(questionDao).createQuestion(Mockito.any(Question.class));
        when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Translation translationRandom = questionService.setAnswerOptions();

        //3. Assert
        Assert.assertNotNull(translationRandom);
    }

    // List<Question> createQuestions(Game game, VocabList chosenVocabList);
    @Test
    @DisplayName("method createQuestions gives something back")
    public void testCreateQuestions() throws CustomOptimisticLockExceptionGame {
        // 1. Arrange
        // s. setup

        // 2. Act
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Mockito.doNothing().when(roundDao).updateRound(Mockito.any(Round.class));
        List<Question> questions= questionService.createQuestions(game, vocabList, round);

        //3. Assert
        Assert.assertNotNull(questions);
        Assert.assertEquals(3, questions.size());
    }

    @Test
    @DisplayName("A question is created")
    public void testCreateQuestion() {
        // 1. Arrange
        // s. setup

        // 2. Act
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        //  Mockito.when(questionService.setAnswerOptions()).thenReturn(translation);

        Question question = questionService.createQuestion(round, vocabList);

        //3. Assert
        Assert.assertNotNull(question);
    }

    // Test wird grün wenn man ihn einzeln ausführt
    @Test
    @DisplayName("A question is created Correctly")
    public void testCreateQuestionCorrectly() {
        // 1. Arrange
        // s. setup

        // 2. Act
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);

        Question question = new Question(round, translation, translation2, translation3, translation4, vocab);
        Translation correctTranslation = question.getRightAnswer();

        // 3. Assert
        Assert.assertEquals(round, question.getRound());
        Assert.assertEquals(translation4, correctTranslation);
        Assert.assertEquals(vocab, question.getVocab());
    }

    // boolean answeredQuestion(String answer, Translation rightAnswer);
    @Test
    @DisplayName("question was answered wrong")
    public void testAnsweredQuestionWrong() {
        // 1. Arrange
        // s. setup
        String wrongAnswer = "Falsche Antwort";

        // 2. Act
        boolean givenAnswer = questionService.answeredQuestion(wrongAnswer, question);

        // 3. Assert
        Assert.assertFalse(givenAnswer);

    }

    @Test
    @DisplayName("question was answered correctly")
    public void testAnsweredQuestionCorrect() {
        // 1. Arrange
        // s. setup

        // 2. Act
        boolean givenAnswer = questionService.answeredQuestion("Translation4", question);

        // 3. Assert
        Assert.assertTrue(givenAnswer);

    }

    // List<Translation> getAllAnswers(Question question);
    @Test
    @DisplayName("The method returns a list with all answer options")
    public void testGetAllAnswers() {
        //1. Arrange --> see setup
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Question question = questionService.createQuestion(round, vocabList);

        //2. Act
        List<Translation> answerOptions = questionService.getAllAnswers(question);

        //3. Assert
        Assert.assertNotNull(answerOptions);
    }

    @Test
    @DisplayName("The method returns the correct list with all answer options")
    public void testGetAllAnswersCorrect() {
        // 1. Arrange --> see setup
        Mockito.when(mockVocabListService.getVocabLists()).thenReturn(vocabLists);
        Question question = questionService.createQuestion(round, vocabList);
        boolean translationInAnswerOption = false;

        // 2. Act
        List<Translation> answerOptions = questionService.getAllAnswers(question);

        for (Translation answerOption : answerOptions) {
            for (String translationString1 : answerOption.getTranslations()) {
                if (translationString1.equals(translationString)) {
                    translationInAnswerOption = true;
                    break;
                }
            }
        }

        // 3. Assert
        Assert.assertEquals(4, answerOptions.size());
        Assert.assertTrue(translationInAnswerOption);
    }
}