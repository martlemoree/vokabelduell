package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public class Vocab {

    private Long vocabId;
    private List<String> vocabs;
    private VocabList vocablist;
    private Translation translation;

    public Vocab(Long vocabId, List<String> vocabs, VocabList vocablist, Translation translation) {
        this.vocabId = vocabId;
        this.vocabs = vocabs;
        this.vocablist = vocablist;
        this.translation = translation;
    }

    public Long getVocabId() {
        return vocabId;
    }

    public void setVocabId(Long vocabId) {
        this.vocabId = vocabId;
    }

    public List<String> getVocabs() {
        return vocabs;
    }

    public void setVocabs(List<String> vocabs) {
        this.vocabs = vocabs;
    }

    public VocabList getVocablist() {
        return vocablist;
    }

    public void setVocablist(VocabList vocablist) {
        this.vocablist = vocablist;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }
}
