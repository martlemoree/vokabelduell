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
     * @param round the round of a game in which the question is created
     * @return  a new question
     */
    Question createQuestion(Round round, VocabList vocabList);

    /**
     * should show all answer options for the question
     * where the entry at index 0 is the correct answer
     * @return list of answer options
     */
    List<Translation> getAllAnswers(List<Question> question, int i);


    /**
     * creates List of questions for a specific round of the game. One round has three questions
     * @param game current game, for the last existing round the questions should be created
     * @param chosenVocabList the chosen vocablist from which the questions should be created
     * @return List of three questions
     */
    List<Question> createQuestions(Game game, VocabList chosenVocabList, Round round);

    /**
     * the answer options for a question should not always be given in the same order
     * e.g. the correct answer is always the first given answer
     * therefore the answer options should be given randomly
     * @param question the question which should be asked
     * @return a string list with the answer options
     */
    List<String> giveAnswerOptionsRandom(List<Question> question, int i);

    /**
     * checks if the given answer from the user is consistent with one of the translations of the right answer
     * @param answer given answer from the user
     * @param questions to get the right answer from
     * @param i list entry of right question
     * @return true if given answer is consistent with rightAnswer, false if not
     */
    boolean answeredQuestion(String answer, List<Question> questions, int i);


    /**
     * holds logic to give a random entry from the list of strings of the given vocab
     * @param questions list of used questions
     * @param i list entry to be addressed
     * @return random string entry from list of strings of vocab
     */
    String giveVocabStringRandom(List<Question> questions, int i);

    //TODO später löschen, nur zum testen
    List<Question> getAllQuestions();
}
