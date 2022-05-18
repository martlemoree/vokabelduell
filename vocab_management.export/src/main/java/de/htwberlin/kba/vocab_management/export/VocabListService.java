package de.htwberlin.kba.vocab_management.export;

import java.io.FileNotFoundException;
import java.util.List;

public interface VocabListService {
    /**
     * creates a new Vocabulary List.
     * @param vocablistId unique identifier of the vocablist
     * @param category category of the vocablist
     * @param name name of the vocablist
     * @param language foreign language of the vocablist
     * @param vocabs list of the vocabularies that belong to this list
     * @return a new Vocabulary List
     */
    VocabList createVocablist(Long vocablistId, String category, String name, String language, List<Vocab> vocabs) throws FileNotFoundException;

    /**
     * changes the Name of the VocabList.
     * @param vocablist the object that should be changed
     * @param newName the old name should be replaced by this name.
     */
    void editName(VocabList vocablist, String newName);

    /**
     * changes the language of the VocabList.
     * @param vocablist the object that should be changed
     * @param newLanguage the old language should be replaced by this language.
     */
    void editLanguage(VocabList vocablist,String newLanguage);

    /**
     * changes the category of the VocabList.
     * @param vocablist the object that should be changed
     * @param newCat the old category should be replaced by this language.
     */
    void editCategory(VocabList vocablist,String newCat);

    /**
     * removes a specific vocabulary from the VocabList.
     * @param vocablist the object that should be changed
     * @param vocab is the vocabulary that should be removed from the VocabList
     */
    public void removeVocab(VocabList vocablist,Vocab vocab);

    /**
     * inserts a new vocabulary to the VocabList
     * @param vocablist the object that should be changed
     * @param vocab the new Vocabulary that should be added to the VocabList
     */
    public void addVocab(VocabList vocablist,Vocab vocab);


    /**
     * create a list that consists of three randomly chosen vocablists. A user can choose between this vocablists before
     * each round.
     * @return the list with three vocablists.
     */
    public List<VocabList> getRandomVocablists();

}
