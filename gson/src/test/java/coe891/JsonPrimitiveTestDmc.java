package coe891;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonPrimitive;
//import org.junit.Test;

public class JsonPrimitiveTestDmc {

  // GetAsString Tests
  // B1: String

  @Test
  public void getAsString_emptyString() {
    JsonPrimitive p = new JsonPrimitive("");
    assertEquals("", p.getAsString());
  }

  @Test
  public void getAsString_normalString() {
    JsonPrimitive p = new JsonPrimitive("hello");
    assertEquals("hello", p.getAsString());
  }

  @Test
  public void getAsString_numericLookingString() {
    JsonPrimitive p = new JsonPrimitive("42");
    assertEquals("42", p.getAsString());
  }

  @Test
  public void getAsString_characterValue() {
    JsonPrimitive p = new JsonPrimitive('A');
    assertEquals("A", p.getAsString());
  }

  // B2: Number

  @Test
  public void getAsString_zeroInteger() {
    JsonPrimitive p = new JsonPrimitive(0);
    assertEquals("0", p.getAsString());
  }

  @Test
  public void getAsString_negativeInteger() {
    JsonPrimitive p = new JsonPrimitive(-1);
    assertEquals("-1", p.getAsString());
  }

  @Test
  public void getAsString_positiveInteger() {
    JsonPrimitive p = new JsonPrimitive(42);
    assertEquals("42", p.getAsString());
  }

  @Test
  public void getAsString_maxInteger() {
    JsonPrimitive p = new JsonPrimitive(Integer.MAX_VALUE);
    assertEquals(String.valueOf(Integer.MAX_VALUE), p.getAsString());
  }

  @Test
  public void getAsString_doubleValue() {
    JsonPrimitive p = new JsonPrimitive(3.14);
    assertEquals("3.14", p.getAsString());
  }

  @Test
  public void getAsString_doubleNaN() {
    JsonPrimitive p = new JsonPrimitive(Double.NaN);
    assertEquals("NaN", p.getAsString());
  }

  // B3: Boolean

  @Test
  public void getAsString_booleanTrue() {
    JsonPrimitive p = new JsonPrimitive(true);
    assertEquals("true", p.getAsString());
  }

  @Test
  public void getAsString_booleanFalse() {
    JsonPrimitive p = new JsonPrimitive(false);
    assertEquals("false", p.getAsString());
  }

  // GetAsNumber Tests
  // B1: Number

  @Test
  public void getAsNumber_zero() {
    JsonPrimitive p = new JsonPrimitive(0);
    assertEquals(0, p.getAsNumber().intValue());
  }

  @Test
  public void getAsNumber_negative() {
    JsonPrimitive p = new JsonPrimitive(-1);
    assertEquals(-1, p.getAsNumber().intValue());
  }

  @Test
  public void getAsNumber_maxValue() {
    JsonPrimitive p = new JsonPrimitive(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, p.getAsNumber().intValue());
  }

  @Test
  public void getAsNumber_double() {
    JsonPrimitive p = new JsonPrimitive(3.14);
    assertEquals(3.14, p.getAsNumber().doubleValue(), 0.001);
  }

  @Test
  public void getAsNumber_nan() {
    JsonPrimitive p = new JsonPrimitive(Double.NaN);
    assertTrue(Double.isNaN(p.getAsNumber().doubleValue()));
  }

  // B2: String

  @Test
  public void getAsNumber_integerString() {
    JsonPrimitive p = new JsonPrimitive("123");
    assertEquals(123, p.getAsNumber().intValue());
  }

  @Test
  public void getAsNumber_decimalString() {
    JsonPrimitive p = new JsonPrimitive("3.14");
    assertEquals(3.14, p.getAsNumber().doubleValue(), 0.001);
  }

  @Test
  public void getAsNumber_negativeString() {
    JsonPrimitive p = new JsonPrimitive("-42");
    assertEquals(-42, p.getAsNumber().intValue());
  }

  // B3: Boolean

  @Test
  public void getAsNumber_booleanTrue() {
    try {
      JsonPrimitive p = new JsonPrimitive(true);
      Number unused = p.getAsNumber();
      assertTrue(false);
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void getAsNumber_booleanFalse() {
    try {
      JsonPrimitive p = new JsonPrimitive(false);
      Number unused = p.getAsNumber();
      assertTrue(false);
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
}
