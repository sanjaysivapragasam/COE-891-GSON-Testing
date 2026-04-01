package com.google.gson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.internal.NonNullElementWrapperList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

public class JsonArray_getAsSingleElement_15_0_Test {

    private JsonArray jsonArray;

    private Method getAsSingleElementMethod;

    @BeforeEach
    public void setUp() throws Exception {
        jsonArray = new JsonArray();
        getAsSingleElementMethod = JsonArray.class.getDeclaredMethod("getAsSingleElement");
        // Allow access to private method
        getAsSingleElementMethod.setAccessible(true);
    }

    @Test
    public void testGetAsSingleElement_sizeOne() throws Exception {
        jsonArray.add(new JsonPrimitive("test"));
        JsonElement result = (JsonElement) getAsSingleElementMethod.invoke(jsonArray);
        assertEquals("test", result.getAsString());
    }

    @Test
    public void testGetAsSingleElement_sizeZero() throws Exception {
        assertThrows(IllegalStateException.class, () -> {
            getAsSingleElementMethod.invoke(jsonArray);
        });
    }

    @Test
    public void testGetAsSingleElement_sizeTwo() throws Exception {
        jsonArray.add(new JsonPrimitive("test"));
        jsonArray.add(new JsonPrimitive("test2"));
        assertThrows(IllegalStateException.class, () -> {
            getAsSingleElementMethod.invoke(jsonArray);
        });
    }
}
