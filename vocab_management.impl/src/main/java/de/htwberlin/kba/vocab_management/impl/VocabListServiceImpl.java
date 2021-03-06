package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.Vocab;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class VocabListServiceImpl implements VocabListService {

    // TODO Weg Wenn getVocabLists mit der DAO richtig implementiert ist
    public static List<VocabList> vocabLists;

    public void removeVocabList(VocabList vocabList){
        //TODO DAO das muss mit der datenbank gemacht werden
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
    public VocabList createVocabList(String text) throws FileNotFoundException {

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
        //TODO ids automatisch generieren
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

          vocabLists.add(v1);
        return v1;
    }

    @Override
    public void editName(VocabList vocabList, String newName) {
        vocabList.setName(newName);
    }

    public List<VocabList> getVocabLists() {

        // TODO Hier muss eigentlich alle vocabLists aus der DAO geholt werden
        // Das ist nur um die Tests zu testen, alles l??schen
        List<String> VocabStrings = Arrays.asList("vocab");
        List<String> TranslationStrings = Arrays.asList("Translation");
        Translation Translation = new Translation(1L, TranslationStrings);
        List<Translation> Translations = Arrays.asList(Translation);

        Vocab Vocab = new Vocab(1L, VocabStrings, Translations);
        List<Vocab> Vocabs = Arrays.asList(Vocab);
        VocabList vocabList = new VocabList(1L,"Category", "Name","Language", Vocabs);

        List<VocabList> vocabLists = Arrays.asList(vocabList);

        return vocabLists;
    }
    // TODO WEG Wenn getVocabLists mit der DAO richtig implementiert ist
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
    //TODO irgendwas mit der DB machen
    }

    public VocabList getVocabListByName(String vocabListName) {
        // TODO DAO
        return null;
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

  public VocabList getVocabListById(Long id) {

        for (VocabList v : vocabLists) {
            if (v.getVocabListId().equals(id)) {
                return v;
            }
        }
        return null;
    }

}