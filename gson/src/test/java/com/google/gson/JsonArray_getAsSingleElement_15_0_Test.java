package com.google.gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonArray_getAsSingleElement_15_0_Test {

    private JsonArray jsonArray;
    private Method getAsSingleElementMethod;

    @BeforeEach
    public void setUp() throws Exception {
        jsonArray = new JsonArray();
        getAsSingleElementMethod = JsonArray.class.getDeclaredMethod("getAsSingleElement");
        getAsSingleElementMethod.setAccessible(true);
    }

    @Test
    public void testGetAsSingleElement_sizeOne() throws Exception {
        jsonArray.add(new JsonPrimitive("test"));

        JsonElement result = (JsonElement) getAsSingleElementMethod.invoke(jsonArray);

        assertEquals("test", result.getAsString());
    }

    @Test
    public void testGetAsSingleElement_sizeZero() {
        InvocationTargetException ex = assertThrows(
            InvocationTargetException.class,
            () -> getAsSingleElementMethod.invoke(jsonArray)
        );

        assertTrue(ex.getCause() instanceof IllegalStateException);
        assertEquals(
            "Array must have size 1, but has size 0",
            ex.getCause().getMessage()
        );
    }

    @Test
    public void testGetAsSingleElement_sizeTwo() {
        jsonArray.add(new JsonPrimitive("test"));
        jsonArray.add(new JsonPrimitive("test2"));

        InvocationTargetException ex = assertThrows(
            InvocationTargetException.class,
            () -> getAsSingleElementMethod.invoke(jsonArray)
        );

        assertTrue(ex.getCause() instanceof IllegalStateException);
        assertEquals(
            "Array must have size 1, but has size 2",
            ex.getCause().getMessage()
        );
    }
}