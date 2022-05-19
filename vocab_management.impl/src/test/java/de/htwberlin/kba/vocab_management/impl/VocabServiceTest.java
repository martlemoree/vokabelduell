package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class VocabServiceTest {

    @InjectMocks
    private VocabServiceImpl service;
    @Mock
    private VocabService vocabService;

    @Before
    public void setUp() {
    }

    @Test
    @DisplayName("new vocab created")
    public void testCreateVocab() {
        // 1. Arrange
        // Mock-Objekte
        Long vocabId = 1234L;
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(123L, "Unit 1", "Verbs", "Englisch", null);
        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);
        // Stubbing
        Mockito.when(vocabService.createVocab(vocabId,vocabs,verbs,translations)).thenReturn(null);

        // 2. Act
        Vocab vocab = service.createVocab(vocabId,vocabs,verbs,translations);

        // 3. Assert
        Assert.assertNull(vocab);
        // Verify
        //Mockito.verify(vocabService, Mockito.times(1)).;
    }

    @Test
    @DisplayName("new single vocab created correctly") // der gleiche wie davor?
    public void testAddVocabsSingle() {
        // 1. Arrange
        // Mock-Objekte
        Long vocabId = 1234L;
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        VocabList verbs = new VocabList(1234L, "Unit 1", "Verbs", "Englisch", null);
        List<String> tranl_string = new ArrayList<String>();
        tranl_string.add("gehen");
        Translation transl = new Translation(234L, tranl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(transl);
        // Stubbing
        Mockito.when(vocabService.createVocab(vocabId,vocabs,verbs,translations)).thenReturn(null);

        // 2. Act
        Vocab vocab = service.createVocab(vocabId,vocabs,verbs,translations);

        // 3. Assert
        Assert.assertEquals(vocabs, vocab.getVocabs());
        // Verify
        //Mockito.verify(vocabService, Mockito.times(1)).;
    }

    @Test
    @DisplayName("new multiple vocab created correctly")
    public void testAddVocabsMultiple() {
        // 1. Arrange
        // Mock-Objekte
        Long vocabId = 1234L;
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
        // Stubbing
        Mockito.when(vocabService.createVocab(vocabId,vocabs,verbs,translations)).thenReturn(null);

        // 2. Act
        Vocab vocab = service.createVocab(vocabId, vocabs, verbs,translations);

        // 3. Assert
        Assert.assertEquals(vocabs, vocab.getVocabs());
        // Verify
        //Mockito.verify(vocabService, Mockito.times(3)).; //anyString
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
