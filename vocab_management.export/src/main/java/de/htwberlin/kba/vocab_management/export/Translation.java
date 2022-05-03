package de.htwberlin.kba.vocab_management.export;
import java.util.List;

public class Translation {
    // Vorschlag wurden in Kempas Beispiel liediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // sinnvoll equals-Methode überschreiben: so können vielleicht Synonyme Übersetzungen mit berücksichtigt werden?

    private long translationId;
    private List<String> translations;
    private Vocab vocab;

    public Translation(long translationId, List<String> translations, Vocab vocab) {
        this.translationId = translationId;
        this.translations = translations;
        this.vocab = vocab;
    }

    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void setTranslations(List<String> translations) {
        this.translations = translations;
    }

    public Vocab getVocab() {
        return vocab;
    }

    public void setVocab(Vocab vocab) {
        this.vocab = vocab;
    }
}
