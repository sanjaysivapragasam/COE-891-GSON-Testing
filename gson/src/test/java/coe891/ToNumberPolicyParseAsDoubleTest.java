package coe891;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.*;

import com.google.gson.JsonParseException;
import com.google.gson.Strictness;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

public class ToNumberPolicyParseAsDoubleTest {
	JsonReader lenientReader, strictReader;
	String nullStr, emptyStr, invalidStr, nanStr, zeroStr, posMinStr, negMinStr, posMaxStr, negMaxStr, posInfStr, negInfStr;
	
	@Before
	public void init() {
		lenientReader = new JsonReader(new StringReader(""));
		lenientReader.setStrictness(Strictness.LENIENT);

		strictReader = new JsonReader(new StringReader(""));
		strictReader.setStrictness(Strictness.STRICT);
		
		nullStr = null;
		emptyStr = "";
		invalidStr = "not a number";
		nanStr = String.valueOf(Double.NaN);
		zeroStr = String.valueOf(0.0);
		posMinStr = String.valueOf(Double.MIN_VALUE);
		negMinStr = String.valueOf(-Double.MIN_VALUE);
		posMaxStr = String.valueOf(Double.MAX_VALUE);
		negMaxStr = String.valueOf(-Double.MAX_VALUE);
		posInfStr = String.valueOf(Double.POSITIVE_INFINITY);
		negInfStr = String.valueOf(Double.NEGATIVE_INFINITY);
	}
	
	@Test
	public void testNull() throws IOException {
		try {
			parseAsDouble(nullStr, lenientReader);
			parseAsDouble(nullStr, strictReader);
			assertTrue(false);
		}
		catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testInvalid() throws IOException {
		try {
			parseAsDouble(invalidStr, lenientReader);
			parseAsDouble(invalidStr, strictReader);
			assertTrue(false);
		} catch (JsonParseException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testNaN() throws IOException {
		Number res = parseAsDouble(nanStr, lenientReader);
		assertEquals(Double.NaN, res);
		
		try {
			parseAsDouble(nanStr, strictReader);
			assertTrue(false);
		} catch (MalformedJsonException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testEmpty() throws IOException {
		try {
			parseAsDouble(emptyStr, lenientReader);
			parseAsDouble(emptyStr, strictReader);
			assertTrue(false);
		} catch (JsonParseException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testZero() throws IOException {
		Number res1 = parseAsDouble(zeroStr, lenientReader);
		Number res2 = parseAsDouble(zeroStr, strictReader);
		
		assertEquals(res1, 0.0);
		assertEquals(res1, res2);
	}
	
	@Test
	public void testMin() throws IOException {
		Number res1 = parseAsDouble(posMinStr, lenientReader);
		Number res2 = parseAsDouble(negMinStr, lenientReader);
		Number res3 = parseAsDouble(posMinStr, strictReader);
		Number res4 = parseAsDouble(negMinStr, strictReader);
		
		assertEquals(res1, Double.MIN_VALUE);
		assertEquals(res2, -Double.MIN_VALUE);
		assertEquals(res1, res3);
		assertEquals(res2, res4);
	}
	
	@Test
	public void testMax() throws IOException {
		Number res1 = parseAsDouble(posMaxStr, lenientReader);
		Number res2 = parseAsDouble(negMaxStr, lenientReader);
		Number res3 = parseAsDouble(posMaxStr, strictReader);
		Number res4 = parseAsDouble(negMaxStr, strictReader);
		
		assertEquals(res1, Double.MAX_VALUE);
		assertEquals(res2, -Double.MAX_VALUE);
		assertEquals(res1, res3);
		assertEquals(res2, res4);
	}
	
	@Test
	public void testInfinity() throws IOException {
		Number res1 = parseAsDouble(posInfStr, lenientReader);
		Number res2 = parseAsDouble(negInfStr, lenientReader);
		assertEquals(res1, Double.POSITIVE_INFINITY);
		assertEquals(res2, Double.NEGATIVE_INFINITY);
		
		try {
			parseAsDouble(posInfStr, strictReader);
			assertTrue(false);
		} catch (MalformedJsonException e) {
			assertTrue(true);
		}
		try {
			parseAsDouble(negInfStr, strictReader);
			assertTrue(false);
		} catch (MalformedJsonException e) {
			assertTrue(true);
		}
	}


	private Number parseAsDouble(String value, JsonReader in) throws IOException {
      try {
        Double d = Double.valueOf(value);
        if ((d.isInfinite() || d.isNaN()) && !in.isLenient()) {
          throw new MalformedJsonException(
              "JSON forbids NaN and infinities: " + d + "; at path " + in.getPreviousPath());
        }
        return d;
      } catch (NumberFormatException e) {
        throw new JsonParseException(
            "Cannot parse " + value + "; at path " + in.getPreviousPath(), e);
      }
    }
}
