package coe891;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonArrayAddTest {

    private JsonArray array;

    @BeforeEach
    void setUp() {
        array = new JsonArray();
    }

    @Test
    void testAdd_ValidString() {
        array.add("hello");

        assertEquals(1, array.size());
        assertTrue(array.get(0).equals(new JsonPrimitive("hello")));
    }

    @Test
    void testAdd_NullString() {
        array.add((String) null);

        assertEquals(1, array.size());
        assertTrue(array.get(0).isJsonNull());
    }

    @Test
    void testAdd_MultipleValues() {
        array.add("coe891");
        array.add("test");

        assertEquals(2, array.size());
        assertTrue(array.contains(new JsonPrimitive("test")));
    }

    @Test
    void testAdd_OrderPreserved() {
        array.add("hello");
        array.add("coe891");

        assertEquals(new JsonPrimitive("hello"), array.get(0));
        assertEquals(new JsonPrimitive("coe891"), array.get(1));
    }

    @Test
    void testAdd_EmptyString() {
        array.add("");

        assertEquals(1, array.size());
        assertEquals(new JsonPrimitive(""), array.get(0));
    }
}