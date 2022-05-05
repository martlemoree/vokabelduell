package de.htwberlin.kba.vocab_management.impl;

import de.htwberlin.kba.vocab_management.export.Vocab;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VocabServiceTest {

    private VocabServiceImpl service;

    @Before
    public void setUp() {
        this.service = new VocabServiceImpl();
    }

    /**
     * Testfall für addVocabs
     */
    @Test
    @DisplayName("new vocab created")
    public void testAddVocabs() {
        // 1. Arrange
        List<String> vocabs = new ArrayList<String>();
        vocabs.add("go");
        vocabs.add("went");
        vocabs.add("gone");

        // 2. Act
        Vocab vocab = service.addVocabs(vocabs);

        // 3. Assert
        Assert.assertNotNull(vocab);
        Assert.assertEquals(vocabs, vocab.getVocabs());
    }
}
