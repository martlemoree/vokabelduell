package de.htwberlin.kba.vocab_management.export;

import java.io.FileNotFoundException;
import java.util.List;

public interface VocabListService {
    /**
     * creates a new Vocabulary List.
     * @param text holds Vocabulary List.
     * @return a new Vocabulary List
     */
    VocabList createVocabList(String text) throws FileNotFoundException;

    /**
     * changes the Name of the VocabList.
     * @param vocabList the object that should be changed
     * @param newName the old name should be replaced by this name.
     */
    void editName(VocabList vocabList, String newName);

    /**
     * changes the language of the VocabList.
     * @param vocabList the object that should be changed
     * @param newLanguage the old language should be replaced by this language.
     */
    void editLanguage(VocabList vocabList,String newLanguage);

    /**
     * changes the category of the VocabList.
     * @param vocabList the object that should be changed
     * @param newCat the old category should be replaced by this language.
     */
    void editCategory(VocabList vocabList,String newCat);

    /**
     * removes a specific vocabulary from the VocabList.
     * @param vocabList the object that should be changed
     * @param vocab is the vocabulary that should be removed from the VocabList
     */
    void removeVocab(VocabList vocabList,Vocab vocab);

    /**
     * inserts a new vocabulary to the VocabList
     * @param vocabList the object that should be changed
     * @param vocab the new Vocabulary that should be added to the VocabList
     */
    void addVocab(VocabList vocabList,Vocab vocab);


    /**
     * create a list that consists of three randomly chosen vocablists. A user can choose between this vocablists before
     * each round.
     * @return the list with three vocablists.
     */
    List<VocabList> getRandomVocabLists();

    void removeVocabList(VocabList vocabList);
    String readFile(String path) throws FileNotFoundException;

    List<VocabList> getVocabLists();

    // wofür ist diese Methode da? Nicht im VokabellduellUiController verwendet
    void setVocabLists(List<VocabList> vocabLists);
    // TODO DAO
    VocabList getVocabListByName( String vocabListName);

}
