package coe891;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonArrayDeepCopyTest {
    private JsonArray array;
    
    @BeforeEach
    public void setUp() {
        array = new JsonArray();
    }
    
    @Test
    public void testDeepCopy_EmptyArray() {
        JsonArray copy = array.deepCopy();
        assertNotSame(array, copy); 
        assertEquals(0, copy.size());
    }

    @Test
    public void testDeepCopy_SingleElement() {
        array.add("hello");
        JsonArray copy = array.deepCopy();
        assertEquals(1, copy.size());
        assertEquals(new JsonPrimitive("hello"), copy.get(0));
        assertEquals(array.get(0), copy.get(0));
    }

    @Test
    public void testDeepCopy_MultipleElements() {
        array.add("coe891");
        array.add("test");
        JsonArray copy = array.deepCopy();
        assertEquals(2, copy.size());
        assertEquals(new JsonPrimitive("coe891"), copy.get(0));
        assertEquals(new JsonPrimitive("test"), copy.get(1));
    }

    @Test
    public void testDeepCopy_WithNullElement() {
        array.add((String) null); 
        JsonArray copy = array.deepCopy();
        assertEquals(1, copy.size());
        assertTrue(copy.get(0).isJsonNull());
    }

    @Test
    public void testDeepCopy_Independence() {
        array.add("idk");
        JsonArray copy = array.deepCopy();
        array.add("coe891");
        assertEquals(1, copy.size());
        assertEquals(2, array.size());
    }
}
