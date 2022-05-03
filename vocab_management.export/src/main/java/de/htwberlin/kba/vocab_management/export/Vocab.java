package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public class Vocab {

    private long vocabId;
    private List<String> vocabs;
    private VocabList vocablist;
    private Translation translation;

    public Vocab(long vocabId, List<String> vocabs) {
        this.vocabId = vocabId;
        this.vocabs = vocabs;
        this.vocablist = vocablist;
    }

    public long getVocabId() {
        return vocabId;
    }

    public void setVocabId(long vocabId) {
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
}
