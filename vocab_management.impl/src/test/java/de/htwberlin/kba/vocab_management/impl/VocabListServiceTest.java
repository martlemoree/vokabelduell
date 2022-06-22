package de.htwberlin.kba.vocab_management.impl;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class VocabListServiceTest {
    @Spy // object is partially mocked. the real methods are being called
    @InjectMocks
    private VocabListServiceImpl service;
    @Mock
    private VocabListDaoImpl vocabListDao;
    @Mock
    private TranslationDaoImpl translationDao;
    @Mock
    private VocabDaoImpl vocabDao;



    @DisplayName("Method returns a list with three vocablists")
    @Test
    public void testgetRandomVocabLists()  {

        // 1. Assert
        List<String> vocabStrings = new ArrayList<>();
        vocabStrings.add("Vocab");
        List<String> translationStrings = new ArrayList<>();
        translationStrings.add("Translation");
        Translation translation = new Translation(translationStrings);

        List<Translation> translations = new ArrayList<>();
        translations.add(translation);

        VocabList vocabList;
        Vocab vocab;

        vocab = new Vocab(vocabStrings, translations);
        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(vocab);

        translation.setVocabs(vocabs);
        vocabList = new VocabList("Category", "Name", "Language", vocabs);

        List<VocabList> vocabLists = new ArrayList<>();

        vocabLists.add(vocabList);

        Mockito.when(vocabListDao.getAllVocabLists()).thenReturn(vocabLists);
        Mockito.when(service.getVocabLists()).thenReturn(vocabLists);


        // 2. Act & 3. Assert
        Assert.assertNotNull(service.getRandomVocabLists());
        Assert.assertEquals(service.getRandomVocabLists().size(), 3);

    }

    @DisplayName("Method returns a String")
    @Test
    public void testreadFile() throws FileNotFoundException {
        // To successfully test this method, put file under given file_path
        String file_path = "C:\\KBA\\vocabulary\\family_and_year.txt";
        Assert.assertNotNull(service.readFile(file_path));
    }

    @DisplayName("Method returns a list of vocablists")
    @Test
    public void testGetVocablists() {

        // 1. Assert
        VocabList vlist1 = new VocabList();
        VocabList vlist2 = new VocabList();
        VocabList vlist3 = new VocabList();
        VocabList vlist4 = new VocabList();

        List<VocabList> vlists = new ArrayList<>();

        vlists.add(vlist1);
        vlists.add(vlist2);
        vlists.add(vlist3);
        vlists.add(vlist4);

        Mockito.when(service.getVocabLists()).thenReturn(vlists);

        Assert.assertNotNull(service.getVocabLists());

    }


    @DisplayName("Method returns a vocablist")
    @Test
    public void testCreateVocabList() {

        // To successfully test this method, put file under given file_path
        //1. Arrange
        String input = "{{{family_and_year}}}{{{English}}}{{{Deutsch}}}{{{schreiner_4_klasse}}}|{brother} : {Bruder}|";
        List<String> transl_string = new ArrayList<>();
        transl_string.add("Bruder");
        Translation t1 = new Translation(transl_string);
        List<Translation> translations = new ArrayList<>();
        translations.add(t1);
        List<String> vocab_string = new ArrayList<>();
        vocab_string.add("brother");
        Vocab v1 = new Vocab(vocab_string, translations);
        List<Vocab> vocabs = new ArrayList<>();
        vocabs.add(v1);
        VocabList vlist = new VocabList("schreiner_4_klasse", "family_and_year", "English", vocabs);


        // 2. Act
       // Mockito.doNothing().when(vocabDao).createVocab(Mockito.any(Vocab.class));
       // Mockito.doNothing().when(translationDao).createTranslation(Mockito.any(Translation.class));
      //  Mockito.doNothing().when(vocabListDao).createVocabList(Mockito.any(VocabList.class));


        // 3. Assert
        Assert.assertNotNull(service.createVocabList(input));
        Assert.assertEquals(service.createVocabList(input).getCategory(), vlist.getCategory());
        Assert.assertEquals(service.createVocabList(input).getLanguage(), vlist.getLanguage());
        Assert.assertEquals(service.createVocabList(input).getName(), vlist.getName());

    }

}
