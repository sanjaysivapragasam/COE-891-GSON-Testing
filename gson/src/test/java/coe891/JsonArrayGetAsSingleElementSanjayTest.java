package coe891;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
//import org.junit.Test;

public class JsonArrayGetAsSingleElementSanjayTest {

  @Test
  public void getAsSingleElement_sizeZero_throws() {
    JsonArray array = new JsonArray();
    try {
      array.getAsString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException ex) {
      assertEquals("Array must have size 1, but has size 0", ex.getMessage());
    }
  }

  @Test
  public void getAsSingleElement_sizeOne_returnsElement() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("hello"));

    assertEquals("hello", array.getAsString());
  }

  @Test
  public void getAsSingleElement_sizeGreaterThanOne_throws() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("a"));
    array.add(new JsonPrimitive("b"));
    try {
      array.getAsString();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException ex) {
      assertEquals("Array must have size 1, but has size 2", ex.getMessage());
    }
  }
}
