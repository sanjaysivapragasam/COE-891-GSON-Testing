package com.google.gson.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

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

        String validInput = "1234567890";
        BigDecimal result = (BigDecimal) method.invoke(numberLimits, validInput);
        assertEquals(new BigDecimal("1234567890"), result);

        String tooLongInput = "A".repeat(MAX_NUMBER_STRING_LENGTH + 1);

        InvocationTargetException ex = assertThrows(
            InvocationTargetException.class,
            () -> method.invoke(numberLimits, tooLongInput)
        );

        assertTrue(ex.getCause() instanceof NumberFormatException);
        assertTrue(ex.getCause().getMessage().contains("Number string too large"));
    }

    @Test
    void checkNumberStringLength() throws Exception {
        Constructor<NumberLimits> constructor = NumberLimits.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        NumberLimits numberLimits = constructor.newInstance();

        Method method = NumberLimits.class.getDeclaredMethod("checkNumberStringLength", String.class);
        method.setAccessible(true);

        String validInput = "1234567890";
        method.invoke(numberLimits, validInput);

        String tooLongInput = "A".repeat(MAX_NUMBER_STRING_LENGTH + 1);

        InvocationTargetException ex = assertThrows(
            InvocationTargetException.class,
            () -> method.invoke(numberLimits, tooLongInput)
        );

        assertTrue(ex.getCause() instanceof NumberFormatException);
        assertTrue(ex.getCause().getMessage().contains("Number string too large"));
    }
}