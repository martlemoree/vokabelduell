package de.htwberlin.kba.vocab_management.impl;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static de.htwberlin.kba.vocab_management.impl.VocabListServiceImpl.vocabLists;
import static org.junit.Assert.assertNotNull;

public class VocabListServiceTest {

    private VocabListService service;

    @Before
    public void setUp() {
        this.service = new VocabListServiceImpl();
    }

/*
    @DisplayName("Tests whether a Vocabulary List is created correctly")
    @Test
    public void testCreateVocabList() throws FileNotFoundException {
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        //2. Assert
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);

        //3. Assert
        Assert.assertNotNull(vlist);
        /*Assert.assertEquals(vocablistId, vlist.getVocablistId());
        Assert.assertEquals(category, vlist.getCategory());
        Assert.assertEquals(name, vlist.getName());
        Assert.assertEquals(language, vlist.getLanguage());

    }

    @DisplayName("checks whether the name is changed correctly.")
    @Test
    public void testEditName() throws FileNotFoundException{
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newName = "Foods";
        List<Vocab> vocabs = new ArrayList<Vocab>();

        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editName(vlist, newName);

        //3. Act
        Assert.assertEquals(newName, vlist.getName());
    }

    @DisplayName("checks whether the language of a vocab list is changed correctly.")
    @Test
    public void testEditLanguage() throws FileNotFoundException{
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newLang = "Spanisch";
        List<Vocab> vocabs = new ArrayList<Vocab>();


        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editLanguage(vlist, newLang);

        //3. Act
        Assert.assertEquals(newLang, vlist.getLanguage());
    }

    @DisplayName("checks whether the category of a vocab list is changed correctly.")
    @Test
    public void testEditCategory() throws FileNotFoundException{
        //1. Arrange
        Long vocablistId = 123456L;
        String category = "Unit 1";
        String name = "Food & Drinks";
        String language = "Englisch";
        String newCat = "Unit 2";
        List<Vocab> vocabs = new ArrayList<Vocab>();


        //2. Act
        VocabList vlist = service.createVocablist(vocablistId, category, name, language, vocabs);
        service.editCategory(vlist,newCat);

        //3. Act
        Assert.assertEquals(newCat, vlist.getCategory());
    }

    @DisplayName("checks whether a vocabulary is added to a vocabulary list correctly.")
    @Test
    public void testAddVocab() throws FileNotFoundException{
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
        service.addVocab(vlist, vocab);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }

    @DisplayName("checks whether a vocabulary is removed from a vocabulary list correctly.")
    @Test
    public void testRemoveVocab() throws FileNotFoundException{
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
        service.addVocab(vlist, vocab1);
        service.addVocab(vlist, vocab2);
        service.removeVocab(vlist, vocab2);

        List<Vocab> checkVocabList = new ArrayList<Vocab>();
        checkVocabList.add(vocab1);

        //3. Act
        Assert.assertEquals(checkVocabList, vlist.getVocabs());
    }



    @Test
    @DisplayName("getRandomVocablists gives back return parameter")
    public void testGetRandomUserVocablistsNotNull(){

        // 2. Act
        List<VocabList> randomLists = service.getRandomVocablists();

        // 3. Assert
        assertNotNull(randomLists);
    }

    @Test
    @DisplayName("getRandomVocablists gives back three vocablists")
    public void testGetRandomUserVocablists(){

        // 2. Act
        List<VocabList> randomLists = service.getRandomVocablists();

        // 3. Assert
        Assert.assertEquals(3, randomLists.size());
    }*/

    @Test
    public void test() throws FileNotFoundException {

        //read file
        File file = new File("C:\\KBA\\vocabulary\\Unit 3 Big dreams - small steps - Part A.txt");
        Scanner sc = new Scanner(file);

        String fileContent = "";
        while(sc.hasNextLine())
            fileContent = fileContent.concat(sc.nextLine() + "|");

        //split text into chars and convert to list of chars
        char[] chars = fileContent.toCharArray();
        List<Character> char_list = new ArrayList<>();
        for (char c : chars) {
            char_list.add(c);
        }
        int len = char_list.size();

        //iterate through the 1st row of the text file and create list with language, category, name
       List<String> strings = new ArrayList<>();
        String returnString = new String();

        for (char c : char_list){
            if (c  == '{' || c  == '}') {
                strings.add(returnString);
                returnString = new String();
            } else if (c == ';') {
                break;
            } else {
                returnString = returnString + c;
            }
        }

        String name_string = strings.get(3);
        String language_string = strings.get(9);
        String category_string = strings.get(21);

        //iterate through the other lines of the text and create a list for every group
        List<String> groups = new ArrayList<>();
        for (int i = 60; i < char_list.size(); i++) {

            if (char_list.get(i)  != '|') {
                returnString = returnString+char_list.get(i);
            } else if (char_list.get(i) == '|') {
                groups.add(returnString);
                returnString = new String();
            }
        }

        groups.remove(0);

        String left = new String();
        String right = new String(); //rechts ist das deutsche, das ist die translation

        List<Vocab> vocabs_init = new ArrayList<>();

        //iterate through every group and create objects
        //todo akuell gibt es weder synonyme noch verschiedene bedeutungen
        //todo ids automatisch generieren
        for (String group: groups) {
            left = group.substring(0,group.indexOf(":")-1);
            right = group.substring(group.indexOf(':')+1);

            //split left part (vocabs) into different objects
           //left = left.replaceAll("[^a-zA-Z ]", "");
            char[] left_chars = left.toCharArray();
            String synoym_left = new String();
            List<Vocab> vocab_list = new ArrayList<>();
            List<String> synonyms_left = new ArrayList<>();

            for (char c : left_chars){
                if (c  == '{') {
                    synoym_left = new String();
                    synonyms_left.clear();
                } else if(c == ',') {
                    synonyms_left.add(synoym_left);
                    synoym_left = new String();
                } else if (c  == '}') {
                    if (!synoym_left.trim().isEmpty()){
                        synonyms_left.add(synoym_left);
                        vocab_list.add(new Vocab(1L, new ArrayList<>(synonyms_left), null));
                        }
                    synonyms_left.clear();
                    synoym_left = new String();
                } else {
                    synoym_left = synoym_left + c;
                }
            }

            //split right part (translations) in different objects
            char[] right_chars = right.toCharArray();
            String synoym = new String();
            List<Translation> translation_list = new ArrayList<>();
            List<String> synonyms = new ArrayList<>();

           for (char c : right_chars){
                if (c  == '{') {
                    synoym = new String();
                    synonyms.clear();
                } else if(c == ',') {
                    synonyms.add(synoym);
                    synoym = new String();
                } else if (c  == '}') {
                    if (!synoym.trim().isEmpty()) {
                        synonyms.add(synoym);
                        translation_list.add(new Translation(1L, new ArrayList<>(synonyms)));
                    }
                    synonyms.clear();
                    synoym = new String();
                } else {
                    synoym = synoym + c;
                }
            }


           //Set relation between vocabs & translations
            for (Vocab v: vocab_list) {
                v.setTranslations(translation_list);
                vocabs_init.add(v);
            }

            for (Translation t: translation_list) {
                t.setVocabs(vocab_list);
            }

        }

       VocabList v1 = new VocabList(1L,category_string, name_string, language_string, vocabs_init);


        //ToDo toString von den Listen anpassen
        System.out.println(v1.getName());
        System.out.println(v1.getLanguage());
        System.out.println(v1.getVocabListId());
        System.out.println(v1.getCategory());
        System.out.println(v1.getVocabs());

       // System.out.println(vocabs_init.get(1));
        // System.out.println(vocabs_init.get(1).getVocabs().size());
      //  vocablists.add(v1);
    }


}
