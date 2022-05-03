package de.htwberlin.kba.vocab_management.export;
import java.util.List;

public class Translation {
    // Vorschlag wurden in Kempas Beispiel liediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // sinnvoll equals-Methode überschreiben: so können vielleicht Synonyme Übersetzungen mit berücksichtigt werden?

    private long translationId;
    private List<String> translations;

    public Translation(long translationId, List<String> translations) {
        this.translationId = translationId;
        this.translations = translations;
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
}
