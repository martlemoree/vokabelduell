package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VocabServiceTest {

    private VocabServiceImpl service;

    @Before
    public void setUp() {
        this.service = new VocabServiceImpl();
    }


    @Test
    @DisplayName("new vocab created")
    public void testCreateVocab() {
        // 1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        // 2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);

        // 3. Assert
        Assert.assertNotNull(vocab);
    }

    @Test
    @DisplayName("new single vocab created correctly")
    public void testAddVocabsSingle() {
        // 1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        // 2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);

        // 3. Assert
        Assert.assertEquals(vocabs, vocab.getVocabs());
    }

    @Test
    @DisplayName("new multiple vocab created correctly")
    public void testAddVocabsMultiple() {
        // 1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        vocabs.add("went");
        vocabs.add("gone");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        // 2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);


        // 3. Assert
        Assert.assertEquals(vocabs, vocab.getVocabs());
    }

    @DisplayName("checks whether the vocablist is changed correctly.")
    @Test
    public void testEditVocablist(){
        //1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);
        VocabList activities = new VocabList(1234L, "Unit 1", "activities", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        //2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);
        service.editVocabList(vocab, activities);

        //3. Act
        Assert.assertEquals(activities, vocab.getVocablist());
    }

    @DisplayName("checks whether the vocabs are changed correctly.")
    @Test
    public void testEditVocabs(){
        //1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        //2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);

        vocabs.add("went");
        vocabs.add("gone");

        service.editVocabs(vocab, vocabs);

        //3. Act
        Assert.assertEquals(vocabs, vocab.getVocabs());
    }

    @DisplayName("checks whether the vocabs are changed correctly.")
    @Test
    public void testEditTranslation(){
        //1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("gift");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);

        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("Geschenk");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);

        //2. Act
        Vocab vocab = service.createVocab(123L, vocabs, verbs,translations);

        List<String> tranl_string2 = new ArrayList<String>();
        tranl_string.add("Gabe");
        Translation transl2 = new Translation(235L, tranl_string);
        translations.add(transl2);

        service.editTranslations(vocab, translations);

        //3. Act
        Assert.assertEquals(translations, vocab.getTranslations());
    }

}
