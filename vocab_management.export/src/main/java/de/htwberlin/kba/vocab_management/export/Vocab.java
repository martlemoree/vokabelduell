package de.htwberlin.kba.vocab_management.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Entity
@Table(name = "vocabs")
public class Vocab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vocab_id")
    private Long vocabId;

    @ElementCollection
    private List<String> vocabs=new ArrayList<>();

    @ManyToMany(mappedBy = "vocabs")
    private List<Translation> translations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vocablist_id", referencedColumnName = "vocablist_id")
    private VocabList vocablist;


    @Autowired
    public Vocab(Long vocabId, List<String> vocabs,  List<Translation> translations) {
        this.vocabId = vocabId;
        this.vocabs = vocabs;
        this.translations = translations;
    }

    public Vocab() {

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
        return "ID=" + vocabId  +
                ", vocabs=" + vocabs  +
                ", translations=" + translations + "\n" ;
    }
}
