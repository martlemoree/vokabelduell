package de.htwberlin.kba.game_management.export;


import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabList;

import java.util.List;

public interface QuestionService {

    /**
     * create a new question including the vocab and the correct translation from the chosen VocabList by the user
     * as well as three wrong answer options
     * @param round the round of a game in which the question is created
     * @return  a new question
     */
    Question createQuestion(Round round, VocabList vocabList);

    /**
     * should show all answer options for the question
     * where the entry at index 0 is the correct answer
     * @param question given question
     * @return list of answer options
     */
    List<Translation> getAllAnswers(Question question);

    /**
     * creates List of questions for a specific round of the game. One round has three questions
     * @param game current game, for the last existing round the questions should be created
     * @param chosenVocabList the chosen vocablist from which the questions should be created
     * @return List of three questions
     * @throws CustomOptimisticLockExceptionGame - is thrown in case of two users are working on the same object. The second user has to reload the object
     */
    List<Question> createQuestions(Game game, VocabList chosenVocabList, Round round) throws CustomOptimisticLockExceptionGame;



    /**
     * checks if the given answer from the user is consistent with one of the translations of the right answer
     * @param answer given answer from the user
     * @param QuestionId object that should be addressed
     * @return true if given answer is consistent with rightAnswer, false if not
     */
    boolean answeredQuestion(String answer, Long QuestionId) throws CustomObjectNotFoundException;

    // TODO später löschen, nur zum testen
    List<Question> getAllQuestions();

    /**
     * searches a question in the database with the given id
     * @param Id that should be searched
     * @return the question
     * @throws CustomObjectNotFoundException - is thrown when the Question cannot be found in the database
     */
    Question getQuestionById(Long Id) throws CustomObjectNotFoundException;
}
