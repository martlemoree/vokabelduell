package de.htwberlin.kba.vocab_management.export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Entity
@Table(name = "translations")
public class Translation {
    // Vorschlag wurden in Kempas Beispiel liediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // sinnvoll equals-Methode überschreiben: so können vielleicht Synonyme Übersetzungen mit berücksichtigt werden?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_id")
    private Long translationId;

    @OneToMany
    @Column(name = "translations")
    private List<String> translations;

    @OneToMany
    @Column(name = "vocabs")
    private  List<Vocab> vocabs;

    public Translation(Long translationId, List<String> translations) {
        this.translationId = translationId;
        this.translations = translations;
    }

    public Translation() {

    }

    public Long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(Long translationId) {
        this.translationId = translationId;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void setTranslations(List<String> translations) {
        this.translations = translations;
    }

    public List<Vocab> getVocabs() {
        return vocabs;
    }

    public void setVocabs(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }

    @Override
    public String toString() {
        String result = "+";
        for (String translation : translations) {
            result += translation + "/";
        }
        return result;
    }
}
