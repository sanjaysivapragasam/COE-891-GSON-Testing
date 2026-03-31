package coe891;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.Test;

public class JsonArrayRemoveSanjayTest {

  @Test
  public void remove_emptyArray_returnsFalse() {
    JsonArray array = new JsonArray();
    boolean removed = array.remove(new JsonPrimitive("x"));

    assertFalse(removed);
    assertEquals(0, array.size());
  }

  @Test
  public void remove_presentElement_returnsTrue() {
    JsonArray array = new JsonArray();
    JsonPrimitive target = new JsonPrimitive("x");
    array.add(target);

    boolean removed = array.remove(target);

    assertTrue(removed);
    assertEquals(0, array.size());
  }

  @Test
  public void remove_absentElement_returnsFalse() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("a"));

    boolean removed = array.remove(new JsonPrimitive("b"));

    assertFalse(removed);
    assertEquals(1, array.size());
  }

  @Test
  public void remove_duplicate_removesFirstOccurrence() {
    JsonArray array = new JsonArray();
    JsonPrimitive target = new JsonPrimitive("dup");

    array.add(target);
    array.add(new JsonPrimitive("middle"));
    array.add(target);

    boolean removed = array.remove(target);

    assertTrue(removed);
    assertEquals(2, array.size());
    assertEquals("middle", array.get(0).getAsString());
  }

  @Test
  public void remove_null_returnsFalse() {
    JsonArray array = new JsonArray();
    array.add(new JsonPrimitive("x"));

    boolean removed = array.remove((JsonElement) null);

    assertFalse(removed);
  }
}
