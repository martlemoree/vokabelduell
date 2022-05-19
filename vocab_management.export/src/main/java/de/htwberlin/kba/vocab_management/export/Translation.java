package de.htwberlin.kba.vocab_management.export;
import java.util.List;

public class Translation {
    // Vorschlag wurden in Kempas Beispiel liediglich in der Entitäten-Klasse umgesetzt (Category),
    // nirgends anders (CategoryServiceImpl/CategoryService)
    // sinnvoll equals-Methode überschreiben: so können vielleicht Synonyme Übersetzungen mit berücksichtigt werden?

    private Long translationId;
    private List<String> translations;

    public Translation(Long translationId, List<String> translations) {
        this.translationId = translationId;
        this.translations = translations;
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

    @Override
    public String toString() {
        String result = "+";
        for (int i = 0; i < translations.size(); i++) {
            result +=  " " + translations.get(i) ;
        }
        return result;
    }
}
