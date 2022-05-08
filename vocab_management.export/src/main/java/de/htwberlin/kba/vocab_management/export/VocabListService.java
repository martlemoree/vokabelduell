package de.htwberlin.kba.vocab_management.export;

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
    public VocabList createVocablist(Long vocablistId, String category, String name, String language, List<Vocab> vocabs);

    /**
     * changes the Name of the VocabList.
     * @param newName the old name should be replaced by this name.
     */
    public void editName(String newName);

    /**
     * changes the language of the VocabList.
     * @param newLanguage the old language should be replaced by this language.
     */
    public void editLanguage(String newLanguage);

    /**
     * changes the category of the VocabList.
     * @param newCat the old category should be replaced by this language.
     */
    public void editCategory(String newCat);

    /**
     * removes a specific vocabulary from the VocabList.
     * @param vocab is the vocabulary that should be removed from the VocabList
     */
    public void removeVocab(Vocab vocab);

    /**
     * inserts a new vocabulary to the VocabList
     * @param vocab the new Vocabulary that should be added to the VocabList
     */
    public void addVocab(Vocab vocab);

    /**
     * User can choose a VocabList from three randomly chosen Vocablists from all available lists
     * @return the chosen Vocablist
     */
    public VocabList chooseVocabList();

    /**
     * Show all existing Vocablists.
     * @return the list with all existing vocablists.
     */
    public List<VocabList> getAllVocablists();

}
