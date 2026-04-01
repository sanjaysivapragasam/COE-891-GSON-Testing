package coe891;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.google.gson.internal.NumberLimits;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Before;
//import org.junit.Test;

public class NumberLimitsParseBigDecimalTest {
  private static final int MAX_NUMBER_STRING_LENGTH = 10_000;

  String nullStr;
  String emptyStr;
  String invalidStr;
  String nanStr;
  String zeroStr;
  String posOneStr;
  String negOneStr;

  String posAlmostBigStr;
  String negAlmostBigStr;
  String posTooBigStr;
  String negTooBigStr;
  String posAlmostBigExpStr;
  String negAlmostBigExpStr;
  String posTooBigExpStr;
  String negTooBigExpStr;

  @BeforeEach
  public void init() {
    nullStr = null;
    emptyStr = "";
    invalidStr = "not a number";
    nanStr = String.valueOf(Double.NaN);
    zeroStr = String.valueOf(0.0);
    posOneStr = String.valueOf(1.0);
    negOneStr = String.valueOf(-1.0);
    posAlmostBigStr = getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH, '1');
    negAlmostBigStr =
        "-" + getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH - 1, '1');
    posTooBigStr = getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH + 1, '1');
    negTooBigStr = "-" + getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH, '1');
    posAlmostBigExpStr = "1E+9999";
    negAlmostBigExpStr = "-1E+9999";
    posTooBigExpStr = "1E+10000";
    negTooBigExpStr = "-1E+10000";
  }

  @Test
  public void testNull() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(nullStr);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testEmpty() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(emptyStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testInvalid() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(invalidStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testNaN() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(nanStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testZero() {
    BigDecimal res1 = NumberLimits.parseBigDecimal(zeroStr);
    BigDecimal res2 = new BigDecimal(zeroStr);
    assertTrue(res1.compareTo(res2) == 0);
  }

  @Test
  public void testOne() {
    BigDecimal res1 = NumberLimits.parseBigDecimal(posOneStr);
    BigDecimal res2 = NumberLimits.parseBigDecimal(negOneStr);
    BigDecimal res3 = new BigDecimal(posOneStr);
    BigDecimal res4 = new BigDecimal(negOneStr);
    assertTrue(res1.compareTo(res3) == 0);
    assertTrue(res2.compareTo(res4) == 0);
    assertTrue(res1.intValue() == -res2.intValue());
  }

  @Test
  public void testAlmostBig() {
    BigDecimal res1 = NumberLimits.parseBigDecimal(posAlmostBigStr);
    BigDecimal res2 = NumberLimits.parseBigDecimal(negAlmostBigStr);
    BigDecimal res3 = new BigDecimal(posAlmostBigStr);
    BigDecimal res4 = new BigDecimal(negAlmostBigStr);
    assertTrue(res1.compareTo(res3) == 0);
    assertTrue(res2.compareTo(res4) == 0);
    assertTrue(res1.intValue() == -res2.intValue());
  }

  @Test
  public void testTooBig() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(posTooBigStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(negTooBigStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testAlmostBigExp() {
    BigDecimal res1 = NumberLimits.parseBigDecimal(posAlmostBigExpStr);
    BigDecimal res2 = NumberLimits.parseBigDecimal(negAlmostBigExpStr);
    BigDecimal res3 = new BigDecimal(posAlmostBigExpStr);
    BigDecimal res4 = new BigDecimal(negAlmostBigExpStr);
    assertTrue(res1.compareTo(res3) == 0);
    assertTrue(res2.compareTo(res4) == 0);
    assertTrue(res1.intValue() == -res2.intValue());
  }

  @Test
  public void testTooBigExp() {
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(posTooBigExpStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
    try {
      BigDecimal unused = NumberLimits.parseBigDecimal(negTooBigExpStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  // Source - https://stackoverflow.com/a/1802944
  // Posted by unwind, modified by community. See post 'Timeline' for change history
  // Retrieved 2026-03-30, License - CC BY-SA 2.5
  protected String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
    if (length > 0) {
      char[] array = new char[length];
      Arrays.fill(array, charToFill);
      return new String(array);
    }
    return "";
  }
}
