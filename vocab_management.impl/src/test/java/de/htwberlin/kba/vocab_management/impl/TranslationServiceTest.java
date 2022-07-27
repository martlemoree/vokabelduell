package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TranslationServiceTest {

    @Spy
    @InjectMocks
    private TranslationServiceImpl service = new TranslationServiceImpl();

    // there are no relevant methods to be tested for the game logic

}