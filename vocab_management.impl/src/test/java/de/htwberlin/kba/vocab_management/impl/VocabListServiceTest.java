package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class VocabListServiceTest {

    private VocabListService service;

    @Before
    public void setUp() {
        this.service = new VocabListServiceImpl();
    }


    @DisplayName("Tests whether a Vocabulary List is created correctly")
    @Test
    public void testCreateVocabList() {
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        //2. Assert
        VocabList vlist = new VocabList(vocablistId, category, name, language,vocabs);

        //3. Assert
        Assert.assertNotNull(vlist);
        Assert.assertEquals(vocablistId, vlist.getVocablistId());
        Assert.assertEquals(category, vlist.getCategory());
        Assert.assertEquals(name, vlist.getName());
        Assert.assertEquals(language, vlist.getLanguage());

    }

    @DisplayName("checks whether the name is changed correctly.")
    @Test
    public void testEditName(){
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newName = "Foods";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editName(newName);

        //3. Act
        Assert.assertEquals(newName, vlist.getName());
    }

    @DisplayName("checks whether the language of a vocab list is changed correctly.")
    @Test
    public void testEditLanguage(){
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newLang = "Spanisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();


        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editLanguage(newLang);

        //3. Act
        Assert.assertEquals(newLang, vlist.getLanguage());
    }

    @DisplayName("checks whether the category of a vocab list is changed correctly.")
    @Test
    public void testEditCategory(){
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newCat = "Unit 2";
        List<Vocab> vocabs = new ArrayList<Vocab>();


        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editLanguage(newCat);

        //3. Act
        Assert.assertEquals(newCat, vlist.getCategory());
    }

    @DisplayName("checks whether a vocabulary is added to a vocabulary list correctly.")
    @Test
    public void testAddVocab(){
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        Long vocabId = 234567L;
        List<String> vocabularies = new ArrayList<String>();
        vocabularies.add("Hello");


        //2. Act
        Vocab vocab = new Vocab(vocabId,vocabularies, null, null);
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.addVocab(vocab);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }

    @DisplayName("checks whether a vocabulary is removed from a vocabulary list correctly.")
    @Test
    public void testRemoveVocab(){
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        Long vocabId = 234567L;
        List<String> vocabularies = new ArrayList<String>();
        vocabularies.add("Hello");


        Long vocabId2 = 234568L;
        List<String> vocabularies2 = new ArrayList<String>();
        vocabularies2.add("Bye");


        //2. Act
        Vocab vocab1 = new Vocab(vocabId, vocabularies,  null, null);
        Vocab vocab2 = new Vocab(vocabId2, vocabularies2,  null, null);
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.addVocab(vocab1);
        service.addVocab(vocab2);
        service.removeVocab(vocab2);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab1);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }

    @Test
    @DisplayName("user chooses the vocablist for the current round")
    public void testChooseUser() {
        // 1. Arrange
        List<VocabList> vocablists = service.getAllVocablists();

        boolean bol = false;

        // 2. Act
        VocabList chosenVocablist = service.chooseVocabList();

        for (int i = 0; i < vocablists.size(); i++) {
            if (chosenVocablist.getVocablistId().equals(vocablists.get(i).getVocablistId())) {
                bol = true;
                break;
            }
            i++;
        }

        // 3. Assert
        assertTrue(bol);
    }
}
