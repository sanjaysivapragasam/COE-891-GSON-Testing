package coe891;

import static org.junit.Assert.assertTrue;

import com.google.gson.internal.NumberLimits;
import java.math.BigInteger;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class NumberLimitsParseBigIntegerTest {
  private static final int MAX_NUMBER_STRING_LENGTH = 10_000;

  String nullStr;
  String emptyStr;
  String invalidStr;
  String zeroStr;
  String posOneStr;
  String negOneStr;
  String posAlmostBigStr;
  String negAlmostBigStr;
  String posTooBigStr;
  String negTooBigStr;

  @Before
  public void init() {
    nullStr = null;
    emptyStr = "";
    invalidStr = "not a number";
    zeroStr = String.valueOf(0);
    posOneStr = String.valueOf(1);
    negOneStr = String.valueOf(-1);
    posAlmostBigStr = getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH, '1');
    negAlmostBigStr =
        "-" + getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH - 1, '1');
    posTooBigStr = getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH + 1, '1');
    negTooBigStr = "-" + getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH, '1');
  }

  @Test
  public void testNull() {
    try {
      BigInteger unused = NumberLimits.parseBigInteger(nullStr);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testEmpty() {
    try {
      BigInteger unused = NumberLimits.parseBigInteger(emptyStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testInvalid() {
    try {
      BigInteger unused = NumberLimits.parseBigInteger(invalidStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testZero() {
    BigInteger res1 = NumberLimits.parseBigInteger(zeroStr);
    BigInteger res2 = new BigInteger(zeroStr);
    assertTrue(res1.equals(res2));
  }

  @Test
  public void testOne() {
    BigInteger res1 = NumberLimits.parseBigInteger(posOneStr);
    BigInteger res2 = NumberLimits.parseBigInteger(negOneStr);
    BigInteger res3 = new BigInteger(posOneStr);
    BigInteger res4 = new BigInteger(negOneStr);
    assertTrue(res1.equals(res3));
    assertTrue(res2.equals(res4));
    assertTrue(res1.intValue() == -res2.intValue());
  }

  @Test
  public void testAlmostBig() {
    BigInteger res1 = NumberLimits.parseBigInteger(posAlmostBigStr);
    BigInteger res2 = NumberLimits.parseBigInteger(negAlmostBigStr);
    BigInteger res3 = new BigInteger(posAlmostBigStr);
    BigInteger res4 = new BigInteger(negAlmostBigStr);
    assertTrue(res1.equals(res3));
    assertTrue(res2.equals(res4));
    assertTrue(res1.intValue() == -res2.intValue());
  }

  @Test
  public void testTooBig() {
    try {
      BigInteger unused = NumberLimits.parseBigInteger(posTooBigStr);
      assertTrue(false);
    } catch (NumberFormatException e) {
      assertTrue(true);
    }
    try {
      BigInteger unused = NumberLimits.parseBigInteger(negTooBigStr);
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
