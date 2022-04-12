package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    int value, value2;
    int motherNatureSteps;
    Card card, card2;

    @BeforeEach
    void setUp() {
        value = 5;
        value2 = 5;
        motherNatureSteps = 1;
        card = new Card(value, motherNatureSteps);
        card2 = new Card(value2, motherNatureSteps);
    }

    @AfterEach
    void tearDown() {
        card = null;
    }

    @Test
    void testEquals() {
        boolean temp = card.equals(card2);
        assertTrue(temp);
    }

    @Test
    void isGreater() {
        value2 = 6;
        boolean temp = card.isGreater(card2);
        assertFalse(temp);
    }
}
