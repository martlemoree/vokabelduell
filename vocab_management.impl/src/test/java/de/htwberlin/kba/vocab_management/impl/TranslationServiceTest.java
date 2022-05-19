package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class TranslationServiceTest {

    //optional

    private TranslationService service;

    @Before
    public void setUp() {
        this.service = new TranslationServiceImpl();
    }

    @DisplayName("Tests whether a Translation is created correctly")
    @Test
    public void testCreateTranslation() {
        //1. Arrange
        Long translationId = 123456L;
        String translationString = "Hello";
        List<String> translations = new ArrayList<>();
        translations.add(translationString);

        //2. Act
        Translation translation = service.createTranslation(translationId, translations);

        //3. Assert
        Assert.assertNotNull(translation);
        Assert.assertEquals(translations, translation.getTranslations());
        Assert.assertEquals(translationId, translation.getTranslationId());
    }

    @Test
    @DisplayName("new translation added correctly")
    public void testAddTranslation() {
        // 1. Arrange
        Long translationId = 123456L;
        String translationString = "Hello";
        List<String> translations = new ArrayList<>();
        translations.add(translationString);
        Translation translation = service.createTranslation(translationId, translations);
        String newTranslation = "Hi";

        // 2. Act
        service.addTranslation(translation,newTranslation);
        translations.add(newTranslation);

        // 3. Assert
        Assert.assertEquals(translations, translation.getTranslations());
    }

    @DisplayName("checks whether a translation is removed from a translation list correctly")
    @Test
    public void testRemoveTranslation() {
        //1. Arrange
        Long translationId = 123456L;
        String translationString = "Hello";
        String newTranslation = "Hi";
        List<String> translations = new ArrayList<>();
        translations.add(translationString);
        translations.add(newTranslation);
        Translation translation = service.createTranslation(translationId, translations);

        //2. Act
        service.removeTranslation(translation, newTranslation);
        translations.remove(newTranslation);

        //3. Act
        Assert.assertEquals(translations, translation.getTranslations());
    }
}