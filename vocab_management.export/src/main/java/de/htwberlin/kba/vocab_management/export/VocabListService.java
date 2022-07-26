package de.htwberlin.kba.vocab_management.export;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
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
     * create a list that consists of three randomly chosen vocablists. A user can choose between this vocablists before
     * each round.
     * @return the list with three vocablists.
     */
    List<VocabList> getRandomVocabLists();

    /**
     * holds logic to delete given vocablist
     * @param vocabList to be deleted
     */
    void removeVocabList(VocabList vocabList);

    /**
     * holds logic to read a text file from given path
     * @param path given path by the user where to find the file
     * @return string with contents of the file
     * @throws FileNotFoundException if file cannot be found in given path
     */
    String readFile(String path) throws FileNotFoundException;

    /**
     * holds logic to get all existing vocablists
     * @return all existing vocablists
     */
    List<VocabList> getVocabLists();

    /**
     * holds logic to get a vocablist by given name
     * @param vocabListName name of the vocablist
     * @return vocablist with given name
     */
    //TODO brauchen wir beides? --> reicht nicht eins von beidem?
    VocabList getVocabListByName( String vocabListName) throws VocabListNotFoundException;

    /**
     * holds logic to get a vocablist by given id
     * @param id of the requested vocablist
     * @return the requested vocabList
     */
    //TODO brauchen wir beides?
    VocabList getVocabListById(Long id) throws VocabListNotFoundException;

}
