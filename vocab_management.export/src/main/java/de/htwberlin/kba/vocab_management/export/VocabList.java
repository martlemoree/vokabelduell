package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public class VocabList {

    private long vocablistId;
    private String category;
    private String name;
    private String language;
    private List<Vocab> vocabs;

    public VocabList(long vocablistId, String category, String name, String language, List<Vocab> vocabs) {
        this.vocablistId = vocablistId;
        this.category = category;
        this.name = name;
        this.language = language;
        this.vocabs = vocabs;
    }

    public long getVocablistId() {
        return vocablistId;
    }

    public void setVocablistId(long vocablistId) {
        this.vocablistId = vocablistId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Vocab> getVocabs() {
        return vocabs;
    }

    public void setVocabs(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }
}
