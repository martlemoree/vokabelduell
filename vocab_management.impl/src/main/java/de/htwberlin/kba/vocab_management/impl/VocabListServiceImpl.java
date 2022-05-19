package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import java.util.List;

public class VocabListServiceImpl implements VocabListService {

    //todo Löschen einer VocabList ergänzen

    //Todo die parameter müssen wieder rausgenommen werden --> wird von der Liste befüllt
    //Todo dafür muss hier aber der dateipfad übergeben werden
    @Override
    public VocabList createVocablist(Long vocablistId, String category, String name, String language, List<Vocab> vocabs) throws FileNotFoundException {
        //read file
        File file = new File("C:\\KBA\\vocabulary\\clothes.txt");
        Scanner sc = new Scanner(file);

        String fileContent = "";
        while(sc.hasNextLine())
            fileContent = fileContent.concat(sc.nextLine() + ";");

        //split text into chars and convert to list of chars
        char[] chars = fileContent.toCharArray();
        List<Character> char_list = new ArrayList<Character>();
        for (char c : chars) {
            char_list.add(c);
        }
        int len = char_list.size();

        //iterate through the 1st row of the text file and create list with language, category, name
        List<String> strings = new ArrayList<String>();
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
        List<String> groups = new ArrayList<String>();
        for (int i = 60; i < char_list.size(); i++) {

            if (char_list.get(i)  != ';') {
                returnString = returnString+char_list.get(i);
            } else if (char_list.get(i) == ';') {
                groups.add(returnString);
                returnString = new String();
            }
        }

        groups.remove(0);

        String left = new String();
        String right = new String();

        int i = 1;

        List<Vocab> vocabs_init = new ArrayList<Vocab>();
        List<Translation> translations_init = new ArrayList<Translation>();
        System.out.println(groups);

        //iterate through every group and create objects
        //todo akuell gibt es weder synonyme noch verschiedene bedeutungen
        for (String group: groups) {
            left = group.substring(0,group.indexOf(':'));
            right = group.substring(group.indexOf(':')+1);

            left = left.replaceAll("[^a-zA-Z]", "");
            right = right.replaceAll("[^a-zA-Z]", "");

            List<String> translations = new ArrayList<String>();
            translations.add(right);

            List<String> vocab_strings = new ArrayList<String>();
            vocab_strings.add(left);

            translations_init.add(new Translation(Long.valueOf(i), translations));
            List<Translation> translation_list = new ArrayList<Translation>();
            translation_list.add(translations_init.get(i-1));

            vocabs_init.add(new Vocab(Long.valueOf(i), vocab_strings, (VocabList) null, translation_list));
            i = i+1;
        }

        VocabList v1 = new VocabList(Long.valueOf(1),category, name, language, vocabs_init);

        for (Vocab v: vocabs_init) {
            v.setVocablist(v1);
        }

        //ToDo toString von den Listen anpassen
        System.out.println(v1.getName());
        System.out.println(v1.getLanguage());
        System.out.println(v1.getVocablistId());
        System.out.println(v1.getCategory());
        System.out.println(v1.getVocabs());

        //Todo das muss noch zur liste an vocablist hinzugefügt --> damit die ID generieren

        return v1;
    }

    @Override
    public void editName(VocabList vocablist, String newName) {

    }

    @Override
    public void editLanguage(VocabList vocablist, String newLanguage) {

    }

    @Override
    public void editCategory(VocabList vocablist, String newCat) {

    }

    @Override
    public void removeVocab(VocabList vocablist, Vocab vocab) {

    }

    @Override
    public void addVocab(VocabList vocablist, Vocab vocab) {

    }
    @Override
    public List<VocabList> getRandomVocablists() {
        return null;
    }

}
