package de.htwberlin.kba.game_management.export;

public interface RoundService {
    /**
     * User can choose a VocabList from three randomly chosen Vocablists from all available lists
     * @return Id of the chosen Vocablist
     */
    public Long chooseVocabList();
}
