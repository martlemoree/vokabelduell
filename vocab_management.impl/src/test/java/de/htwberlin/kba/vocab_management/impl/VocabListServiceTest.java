package de.htwberlin.kba.vocab_management.impl;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl.vocabLists;
import static org.junit.Assert.assertNotNull;

public class VocabListServiceTest {

    VocabListDao vocabListDao;
    private VocabListService service = new VocabListServiceImpl(vocabListDao);
    @Mock
    List<Vocab> mock_vocabs;

    @DisplayName("Method returns a Vocablist")
    @Test
    public void testgetVocabListByName()  {
        //1. Arrange
        VocabList vlist = new VocabList(1L, "category", "name", "language", mock_vocabs);

        //TODO hier noch den richtigen Test für die Datenbank einfügen
        // Vocablist wird in die Datenbank eingefügt und muss dann gefunden werden

        // 2. Act & 3. Assert
        Assert.assertNotNull(service.getVocabListByName("name"));
        Assert.assertEquals(service.getVocabListByName("name").getName(), vlist.getName());
        Assert.assertEquals(service.getVocabListByName("name").getLanguage(), vlist.getLanguage());
        Assert.assertEquals(service.getVocabListByName("name").getCategory(), vlist.getCategory());

    }



    @DisplayName("Method returns a list with three vocablists")
    @Test
    public void testgetRandomVocabLists()  {

        // 2. Act & 3. Assert
        Assert.assertNotNull(service.getRandomVocabLists());
        Assert.assertEquals(service.getRandomVocabLists().size(), 3);

    }

    //TODO brauchen wir die methode überhaupt? wir haben ja schon per name
    @DisplayName("Method returns a Vocablist")
    @Test
    public void testgetVocabListById()  {
        //1. Arrange
        VocabList vlist = new VocabList(1L, "category", "name", "language", mock_vocabs);

        //TODO hier noch den richtigen Test für die Datenbank einfügen
        // Vocablist wird in die Datenbank eingefügt und muss dann gefunden werden

        // 2. Act & 3. Assert
        Assert.assertNotNull(service.getVocabListById(1L));
        Assert.assertEquals(service.getVocabListById(1L).getName(), vlist.getName());
        Assert.assertEquals(service.getVocabListById(1L).getLanguage(), vlist.getLanguage());
        Assert.assertEquals(service.getVocabListById(1L).getCategory(), vlist.getCategory());

    }

    @DisplayName("Method returns a String")
    @Test
    public void testreadFile() throws FileNotFoundException {
        String file_path = "C:\\KBA\\vocabulary\\family_and_year.txt";
        Assert.assertNotNull(service.readFile(file_path));

    }

    @DisplayName("Method returns a vocablist")
    @Test
    public void testCreateVocabList() throws FileNotFoundException {
        //1. Arrange
        String input = "{{{family_and_year}}}{{{English}}}{{{Deutsch}}}{{{schreiner_4_klasse}}}|{brother} : {Bruder}|";
        List<String> transl_string = new ArrayList<>();
        transl_string.add("Bruder");
        Translation t1 = new Translation(1L, transl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(t1);
        List<String> vocab_string = new ArrayList<>();
        vocab_string.add("brother");
        Vocab v1 = new Vocab(1L, vocab_string, translations);
        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(v1);
        VocabList vlist = new VocabList(1L,"schreiner_4_klasse", "family_and_year", "English", vocabs);

        // 2. Act & 3. Assert
        Assert.assertNotNull(service.createVocabList(input));
        Assert.assertEquals(service.createVocabList(input).getCategory(), vlist.getCategory());
        Assert.assertEquals(service.createVocabList(input).getLanguage(), vlist.getLanguage());
        Assert.assertEquals(service.createVocabList(input).getName(), vlist.getName());

    }


}
