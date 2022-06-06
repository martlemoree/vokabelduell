package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class TranslationServiceImpl implements TranslationService {

    TranslationDao translationDao;

    @Autowired
    public TranslationServiceImpl(TranslationDao translationDao) {
        this.translationDao = translationDao;
    }


    public Translation createTranslation(Long translationId, List<String> translations){

        return new Translation(translationId, translations) ;}
    public void removeTranslation(Translation translation,String translationToBeRemoved){
        //Todo irgendwas in der DB machen
    }
}