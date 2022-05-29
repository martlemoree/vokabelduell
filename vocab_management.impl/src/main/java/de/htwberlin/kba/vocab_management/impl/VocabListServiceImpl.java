package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import java.util.List;

@Service
public class VocabListServiceImpl implements VocabListService {

    public static List<VocabList> vocabLists;

    public void removeVocabList(VocabList vocabList){
        //todo das muss mit der datenbank gemacht werden
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
    public VocabList createVocabList(String text) {

        //split text into chars and convert to list of chars
        char[] chars = text.toCharArray();
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
        List<Translation> translations_init = new ArrayList<>();

        //iterate through every group and create objects
        //todo ids automatisch generieren
        for (String group: groups) {
            left = group.substring(0,group.indexOf(':')-1);
            right = group.substring(group.indexOf(':')+1);

            left = left.replaceAll("[^a-zA-Z ]", "");

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
                    synonyms.add(synoym);
                    translation_list.add(new Translation(1L, new ArrayList<>(synonyms) ));
                    synonyms.clear();
                } else {
                    synoym = synoym + c;
                }
            }

            //System.out.println(translation_list);
            List<String> vocab_strings = new ArrayList<>();
            vocab_strings.add(left);

            vocabs_init.add(new Vocab(1L, vocab_strings, translation_list));
            // System.out.println(vocabs_init);

        }
        VocabList v1 = new VocabList(1L,category_string, name_string, language_string, vocabs_init);

        System.out.println(v1.getName());
        System.out.println(v1.getLanguage());
        System.out.println(v1.getVocabListId());
        System.out.println(v1.getCategory());
        System.out.println(v1.getVocabs());

          vocabLists.add(v1);
        return v1;
    }

    @Override
    public void editName(VocabList vocabList, String newName) {
        vocabList.setName(newName);
    }
    public List<VocabList> getVocabLists() {
        return vocabLists;
    }

    public void setVocabLists(List<VocabList> vocabList) {
        VocabListServiceImpl.vocabLists = vocabList;
    }
    @Override
    public void editLanguage(VocabList vocabList, String newLanguage) {
        vocabList.setLanguage(newLanguage);
    }

    @Override
    public void editCategory(VocabList vocabList, String newCat) {
        vocabList.setCategory(newCat);
    }

    @Override
    public void removeVocab(VocabList vocabList, Vocab vocab) {
    //todo irgendwas mit der DB machen
    }

    @Override
    public void addVocab(VocabList vocabList, Vocab vocab) {
        List<Vocab> current_vocabs = vocabList.getVocabs();
        current_vocabs.add(vocab);
        vocabList.setVocabs(current_vocabs);
    }
    @Override
    public List<VocabList> getRandomVocabLists() {

        List<Long> allVocabLists = new ArrayList<>();

        for (VocabList vlist: vocabLists) {
            allVocabLists.add(vlist.getVocabListId());
        }

        Random rand = new Random();
        int element1 = rand.nextInt(allVocabLists.size());
        int element2 = rand.nextInt(allVocabLists.size());
        int element3 = rand.nextInt(allVocabLists.size());

        List<VocabList> random_lists = new ArrayList<>();

        random_lists.add(getVocabListById(allVocabLists.get(element1)));
        random_lists.add(getVocabListById(allVocabLists.get(element2)));
        random_lists.add(getVocabListById(allVocabLists.get(element3)));

        return random_lists;
    }
    //todo problem hier: die linke seite für synonyme oder verschiedene bedeutungen zu machen
    // da die translation jetzt keine lsite an vocabs mehr hat, können wir nicht mehrere vocabs zusammenfügen
    // man könnte dann einfach eine liste an strings machen für eine vocab aber
    // beispiel 1: {tell}, {told}, {told} : {erzählen}, {vorhersagen}, {sagen}
    // beispiel 2: {the sooner ...\, the better  ...} : {je eher ..\, desto besser ...}
    // beispiel 3: {lab, laboratory} : {Labor}
    // beispiel 4: {(to) burst\, burst\, burst} : {platzen}

  public VocabList getVocabListById(Long id) {

        for (VocabList v : vocabLists) {
            if (v.getVocabListId().equals(id)) {
                return v;
            }
        }
        return null;
    }

}