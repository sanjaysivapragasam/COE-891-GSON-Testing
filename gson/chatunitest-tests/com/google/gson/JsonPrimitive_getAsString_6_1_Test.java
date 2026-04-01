package com.google.gson;

import java.lang.reflect.Field;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

// ... other imports
public class JsonPrimitive_getAsString_6_1_Test {

    @Test
    public void testGetAsString() throws NoSuchFieldException, IllegalAccessException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive("Hello");
        String result = jsonPrimitive.getAsString();
        assertEquals("Hello", result);
        jsonPrimitive = new JsonPrimitive(123);
        result = jsonPrimitive.getAsString();
        assertEquals("123", result);
        // ... other tests
    }

    @Test
    public void testGetAsBoolean() throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
        boolean result = (boolean) jsonPrimitive.getClass().getMethod("getAsBoolean").invoke(jsonPrimitive);
        assertTrue(result);
        // ... other tests
    }

    @Test
    public void testGetAsNumber() throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
        Number result = (Number) jsonPrimitive.getClass().getMethod("getAsNumber").invoke(jsonPrimitive);
        assertEquals(123, result);
        // ... other tests
    }

    @Test
    public void testGetAsDouble() throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(123.456);
        double result = (double) jsonPrimitive.getClass().getMethod("getAsDouble").invoke(jsonPrimitive);
        assertEquals(123.456, result);
        // ... other tests
    }

    @Test
    public void testGetAsBigDecimal() throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(new BigDecimal("123.456"));
        BigDecimal result = (BigDecimal) jsonPrimitive.getClass().getMethod("getAsBigDecimal").invoke(jsonPrimitive);
        assertEquals(new BigDecimal("123.456"), result);
        // ... other tests
    }

    @Test
    public void testGetPrivateFieldValue() throws NoSuchFieldException, IllegalAccessException {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
        Field field = JsonPrimitive.class.getDeclaredField("value");
        field.setAccessible(true);
        Object privateValue = field.get(jsonPrimitive);
        assertNotNull(privateValue);
    }
}
