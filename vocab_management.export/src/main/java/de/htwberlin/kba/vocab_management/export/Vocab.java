package de.htwberlin.kba.vocab_management.export;

import java.util.List;

public class Vocab {
    private Long vocabId;
    private List<String> vocabs;
    private List<Translation> translations;

    public Vocab(Long vocabId, List<String> vocabs,  List<Translation> translations) {
        this.vocabId = vocabId;
        this.vocabs = vocabs;
        this.translations = translations;
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


    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        /*String result = "+";
        for (int i = 0; i < vocabs.size(); i++) {
            result += " " + vocabs.get(i)  ;
        }
        return result;*/

        return "ID=" + vocabId  +
                ", vocabs=" + vocabs  +
                ", translations=" + translations + "\n" ;
    }
}
