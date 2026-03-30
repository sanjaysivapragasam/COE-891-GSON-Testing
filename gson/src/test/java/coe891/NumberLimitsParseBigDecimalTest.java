package coe891;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.*;

import com.google.gson.internal.NumberLimits;

public class NumberLimitsParseBigDecimalTest {
	private static final int MAX_NUMBER_STRING_LENGTH = 10_000;

	String nullStr, emptyStr, invalidStr, nanStr, zeroStr, posOneStr, negOneStr;
	String posAlmostBigStr, negAlmostBigStr, posTooBigStr, negTooBigStr, posAlmostBigExpStr, negAlmostBigExpStr, posTooBigExpStr, negTooBigExpStr;
	
	@Before
	public void init() {
		nullStr = null;
		emptyStr = "";
		invalidStr = "not a number";
		nanStr = String.valueOf(Double.NaN);
		zeroStr = String.valueOf(0.0);
		posOneStr = String.valueOf(1.0);
		negOneStr = String.valueOf(-1.0);
		posAlmostBigStr = getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH, '1');
		negAlmostBigStr = "-" + getStringWithLengthAndFilledWithCharacter(MAX_NUMBER_STRING_LENGTH - 1, '1');
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
			NumberLimits.parseBigDecimal(nullStr);
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testEmpty() {
		try {
			NumberLimits.parseBigDecimal(emptyStr);
			assertTrue(false);
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testInvalid() {
		try {
			NumberLimits.parseBigDecimal(invalidStr);
			assertTrue(false);
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testNaN() {
		try {
			NumberLimits.parseBigDecimal(nanStr);
			assertTrue(false);
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testZero() {
		BigDecimal res1 = NumberLimits.parseBigDecimal(zeroStr);
		BigDecimal res2 = new BigDecimal(zeroStr);
		assertTrue(res1.equals(res2));
	}
	
	@Test
	public void testOne() {
		BigDecimal res1 = NumberLimits.parseBigDecimal(posOneStr);
		BigDecimal res2 = NumberLimits.parseBigDecimal(negOneStr);
		BigDecimal res3 = new BigDecimal(posOneStr);
		BigDecimal res4 = new BigDecimal(negOneStr);
		assertTrue(res1.equals(res3));
		assertTrue(res2.equals(res4));
		assertTrue(res1.intValue() == -res2.intValue());
	}
	
	@Test
	public void testAlmostBig() {
		BigDecimal res1 = NumberLimits.parseBigDecimal(posAlmostBigStr);
		BigDecimal res2 = NumberLimits.parseBigDecimal(negAlmostBigStr);
		BigDecimal res3 = new BigDecimal(posAlmostBigStr);
		BigDecimal res4 = new BigDecimal(negAlmostBigStr);
		assertTrue(res1.equals(res3));
		assertTrue(res2.equals(res4));
		assertTrue(res1.intValue() == -res2.intValue());
	}
	
	@Test
	public void testTooBig() {
		try {
			NumberLimits.parseBigDecimal(posTooBigStr);
			assertTrue(false);
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
		try {
			NumberLimits.parseBigDecimal(negTooBigStr);
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
		assertTrue(res1.equals(res3));
		assertTrue(res2.equals(res4));
		assertTrue(res1.intValue() == -res2.intValue());
	}
	
	@Test
	public void testTooBigExp() {
		try {
			NumberLimits.parseBigDecimal(posTooBigExpStr);
			assertTrue(false);
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
		try {
			NumberLimits.parseBigDecimal(negTooBigExpStr);
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
