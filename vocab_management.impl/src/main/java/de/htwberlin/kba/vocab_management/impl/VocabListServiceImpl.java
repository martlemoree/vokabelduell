package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import java.util.List;

@Service
public class VocabListServiceImpl implements VocabListService {
    private VocabListDao vocabListDao;

    @Autowired
    public VocabListServiceImpl(VocabListDao vocabListDao) {
        this.vocabListDao = vocabListDao;
    }

    public void removeVocabList(VocabList vocabList){
        // method not implemented and tested because it is not part of the game logic
    }

    public String readFile(String path) throws FileNotFoundException {
        //read file
        File file = new File(path);
        Scanner sc = new Scanner(file);

        String fileContent = "";
        while(sc.hasNextLine())
            fileContent = fileContent.concat(sc.nextLine() + "|");

        return fileContent;
    }

    @Override
    public VocabList createVocabList(String text)  {

        TranslationDao translationDao = new TranslationDaoImpl();
        VocabDao vocabDao = new VocabDaoImpl();

        //split text into chars and convert to list of chars
        char[] chars = text.toCharArray();
        List<Character> char_list = new ArrayList<>();
        for (char c : chars) {
            char_list.add(c);
        }

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
                        vocab_list.add(new Vocab(new ArrayList<>(synonyms_left), null));
                        vocabDao.createVocab(vocab_list.get(vocab_list.size()-1));
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
                        translation_list.add(new Translation(new ArrayList<>(synonyms)));
                        translationDao.createTranslation(translation_list.get(translation_list.size()-1));
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

        VocabList v1 = new VocabList(category_string, name_string, language_string, vocabs_init);
        vocabListDao.createVocabList(v1);

        return v1;
    }

    @Override
    public void editName(VocabList vocabList, String newName) {
        // method not implemented and tested because it is not part of the game logic
        vocabList.setName(newName);

    }

    @Override
    public List<VocabList> getVocabLists() {
        return vocabListDao.getAllVocabLists();
    }

    @Override
    public void editLanguage(VocabList vocabList, String newLanguage) {
        // method not implemented and tested because it is not part of the game logic
        vocabList.setLanguage(newLanguage);
    }

    @Override
    public void editCategory(VocabList vocabList, String newCat) {
        // method not implemented and tested because it is not part of the game logic
        vocabList.setCategory(newCat);
    }

    @Override
    public void removeVocab(VocabList vocabList, Vocab vocab) {
        // method not implemented and tested because it is not part of the game logic
    }

    public VocabList getVocabListByName(String vocabListName) {
        // method not implemented and tested because it is not part of the game logic
        return null;
    }


    @Override
    public VocabList getVocabListById(Long id) {
        vocabListDao.getVocabListById(id);
        return null;
    }

    @Override
    public List<VocabList> getRandomVocabLists() {

        List<VocabList> allVocabLists = getVocabLists();

        Random rand = new Random();
        int element1 = rand.nextInt(allVocabLists.size());
        int element2 = rand.nextInt(allVocabLists.size());
        int element3 = rand.nextInt(allVocabLists.size());

        List<VocabList> random_lists = new ArrayList<>();

        random_lists.add(allVocabLists.get(element1));
        random_lists.add(allVocabLists.get(element2));
        random_lists.add(allVocabLists.get(element3));

        return random_lists;
    }


}