package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface QuestionService {

    /**
     * create a new question including the vocab and the correct translation from the chosen VocabList by the user
     * as well as three wrong answer options
     * @param questionId the unique identifier of the question
     * @param round the round of a game in which the question is created
     * @return  a new question
     */
    Question createQuestion(Long questionId, Round round, VocabList vocabList);

    /**
     * A translation is randomly generated for a wrong answer option to the question.
     */
    Translation setAnswerOptions();

    /**
     * should show all answer options for the question
     * where the entry at index 0 is the correct answer
     * @return the list of answer options
     */
    List<Translation> getAllAnswers(Question question);

    // TODO: Javadoc
    List<Question> createQuestions(Game game, VocabList chosenVocabList);
    List<String> giveAnswerOptionsRandom(Question question);

    boolean answeredQuestion(String answer, Translation rightAnswer);

}
