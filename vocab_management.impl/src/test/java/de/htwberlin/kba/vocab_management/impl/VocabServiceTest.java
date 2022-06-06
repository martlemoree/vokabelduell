package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class VocabServiceTest {

    //optional
    private VocabDao vocabDao;
    private VocabServiceImpl service = new VocabServiceImpl(vocabDao);

    @Mock
    List<Translation> mock_translations;
    @Mock
    private VocabList mock_vocabList;
    @Mock
    private Vocab mock_vocab;
   // removeVocab
   // getVocabByName

    @Test
    @DisplayName("the method should return a vocab")
    public void testgetVocabByNameNotNull(){
        //1. Arrange
        List<String> vocab_strings = new ArrayList<>();
        vocab_strings.add("Hallo");
        vocab_strings.add("Guten Tag");
        Vocab v1 = new Vocab(1L, vocab_strings,mock_translations);

        //TODO hier noch den richtigen Test für die Datenbank einfügen
        // Vocab wird in die Datenbank eingefügt und muss dann gefunden werden

        //TODO eine vocab hat keinen namen sondern nur eine string liste
        // 3. Assert
       //Assert.assertNotNull(service.getVocabByName(vocab_strings));
       //Assert.assertEquals(service.getVocabByName(vocab_strings), v1);
    }

    @Test
    @DisplayName("the method should return a string that is not empty")
    public void testGiveVocabStringRandomNotNull(){
        //1. Arrange
        List<String> vocab_strings = new ArrayList<>();
        vocab_strings.add("Hallo");
        vocab_strings.add("Guten Tag");
        Vocab v1 = new Vocab(1L, vocab_strings,mock_translations);

        //2. Act & 3. Assert
        Assert.assertNotNull(service.giveVocabStringRandom(v1));
    }

    @Test
    @DisplayName("the method should return a string from the string list of a vocab")
    public void testGiveVocabStringRandominList(){
        //1. Assert
        List<String> vocab_strings = new ArrayList<>();
        vocab_strings.add("Hallo");
        vocab_strings.add("Guten Tag");
        Vocab v1 = new Vocab(1L, vocab_strings,mock_translations);

        //2. Act
        String test_string = service.giveVocabStringRandom(v1);

        //3. Assert
        Assert.assertTrue(v1.getVocabs().contains(test_string));


    }


}
