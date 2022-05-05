package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VocabListServiceTest {

    private VocabListService service;

    @Before
    public void setUp() {
        this.service = new VocabListServiceImpl();
    }

    /**
     * Tests whether a Vocabulary List is created correctly
     */
    @Test
    public void testCreateVocabList() throws Exception {
        //1. Arrange
        long vocablistId = 123456;
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

    /**
     * checks whether the name is changed correctly.
     */
    @Test
    public void testEditName() throws Exception{
        //1. Arrange
        long vocablistId = 123456;
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

    /**
     * checks whether the language of a vocab list is changed correctly.
     */
    @Test
    public void testEditLanguage() throws Exception{
        //1. Arrange
        long vocablistId = 123456;
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

    /**
     * checks whether the category of a vocab list is changed correctly.
     */
    @Test
    public void testEditCategory() throws Exception{
        //1. Arrange
        long vocablistId = 123456;
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

    /**
     * checks whether a vocabulary is added to a vocabulary list correctly.
     * @throws Exception
     */
    @Test
    public void testAddVocab() throws Exception{
        //1. Arrange
        long vocablistId = 123456;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newLang = "Spanisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        Long vocabId = Long.valueOf(234567);
        List<String> vocabularies = new ArrayList<String>();
        vocabularies.add("Hello");


        //2. Act
        Vocab vocab = new Vocab(vocabId,vocabs);
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.addVocab(vocab);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }

    /**
     * checks whether a vocabulary is removed from a vocabulary list correctly.
     * @throws Exception
     */
    @Test
    public void testRemoveVocab() throws Exception{
        //1. Arrange
        long vocablistId = 123456;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newLang = "Spanisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        long vocabId = 234567;
        List<String> vocabularies = new ArrayList<String>();
        vocabularies.add("Hello");

        long vocabId2 = 234568;
        List<String> vocabularies2 = new ArrayList<String>();
        vocabularies2.add("Bye");


        //2. Act
        Vocab vocab1 = new Vocab(vocabId,vocabularies);
        Vocab vocab2 = new Vocab(vocabId2,vocabularies2);
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.addVocab(vocab1);
        service.addVocab(vocab2);
        service.removeVocab(vocab2);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab1);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }
}
