package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class TranslationServiceTest {

    //optional
    private TranslationDao translationDao;
    private TranslationService service = new TranslationServiceImpl(translationDao);;

}