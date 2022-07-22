package de.htwberlin.kba.vocab_management.export;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name="getAllVocabs", query="FROM Vocab AS vocabs")
@Entity
@Table(name = "vocabs")
public class Vocab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vocab_id")
    private Long vocabId;

    @ElementCollection
    private List<String> vocabs=new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Translation> translations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vocablist_id", referencedColumnName = "vocablist_id")
   // @JsonBackReference
    private VocabList vocablist;

    @Version
    private Integer version;

    public Vocab( List<String> vocabs,  List<Translation> translations) {
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
        Hibernate.initialize(this.vocabs);
        return vocabs;
    }

    public void setVocabs(List<String> vocabs) {
        this.vocabs = vocabs;
    }


    public List<Translation> getTranslations() {
        Hibernate.initialize(this.translations);
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
