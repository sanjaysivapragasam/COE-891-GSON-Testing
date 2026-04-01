package com.google.gson.internal;

import java.lang.reflect.*;
import java.math.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberLimits_parseBigDecimal_1_1_Test {

    private static final int MAX_NUMBER_STRING_LENGTH = 10_000;

    @Test
    void parseBigDecimal() throws Exception {
        Constructor<NumberLimits> constructor = NumberLimits.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        NumberLimits numberLimits = constructor.newInstance();
        Method method = NumberLimits.class.getDeclaredMethod("parseBigDecimal", String.class);
        method.setAccessible(true);
        // Test valid input
        String validInput = "1234567890";
        BigDecimal result = (BigDecimal) method.invoke(numberLimits, validInput);
        assertEquals(new BigDecimal("1234567890"), result);
        // Test invalid input - too long
        String tooLongInput = new String(new char[MAX_NUMBER_STRING_LENGTH + 1]).replace('\0', 'A');
        assertThrows(NumberFormatException.class, () -> method.invoke(numberLimits, tooLongInput));
        // Test invalid input - unsupported scale
        String unsupportedScaleInput = "12345678901234567890";
        assertThrows(NumberFormatException.class, () -> method.invoke(numberLimits, unsupportedScaleInput));
    }

    @Test
    void checkNumberStringLength() throws Exception {
        Constructor<NumberLimits> constructor = NumberLimits.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        NumberLimits numberLimits = constructor.newInstance();
        Method method = NumberLimits.class.getDeclaredMethod("checkNumberStringLength", String.class);
        method.setAccessible(true);
        // Test valid input
        String validInput = "1234567890";
        method.invoke(numberLimits, validInput);
        // Test invalid input - too long
        String tooLongInput = new String(new char[MAX_NUMBER_STRING_LENGTH + 1]).replace('\0', 'A');
        assertThrows(NumberFormatException.class, () -> method.invoke(numberLimits, tooLongInput));
    }
}

class NumberLimits_Constructor_Test {

    @Test
    void constructor() throws Exception {
        Class<?> clazz = Class.forName("com.google.gson.internal.NumberLimits");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        // Test that the constructor is private and cannot be instantiated
        assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
    }
}
