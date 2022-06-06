package de.htwberlin.kba.vocab_management.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "vocab_lists")
public class VocabList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vocablist_id")
    private Long vocablistId;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private String language;

    @ManyToOne
    @JoinColumn
    private List<Vocab> vocabs;



    @Autowired
    public VocabList(Long vocablistId, String category, String name, String language, List<Vocab> vocabs) {
        this.vocablistId = vocablistId;
        this.category = category;
        this.name = name;
        this.language = language;
        this.vocabs = vocabs;
    }

    public VocabList() {

    }

    public Long getVocabListId() {
        return vocablistId;
    }

    public void setVocablistId(Long vocablistId) {
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
