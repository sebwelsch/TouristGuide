package kea.touristguide.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TouristAttractionTest {
    private TouristAttraction t;

    @BeforeEach
    void setUp() {
        t = new TouristAttraction("Tivoli", "Forlystelser", "København", Collections.singletonList("Underholdning"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Tivoli", t.getName());
    }

    @Test
    void setName() {
        t.setName("Bakken");
        assertEquals("Bakken", t.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Forlystelser", t.getDescription());
    }

    @Test
    void setDescription() {
        t.setDescription("Park");
        assertEquals("Park", t.getDescription());
    }

    @Test
    void getCity() {
        assertEquals("København", t.getCity());
    }

    @Test
    void setCity() {
        t.setCity("Kgs. Lyngby");
        assertEquals("Kgs. Lyngby", t.getCity());
    }

    @Test
    void getTags() {
        assertEquals(Collections.singletonList("Underholdning"), t.getTags());
    }

    @Test
    void setTags() {
        t.setTags(Collections.singletonList("Børn"));
        assertEquals(Collections.singletonList("Børn"), t.getTags());
    }
}