package org.jakubhajek.tbcurrency;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jakubhajek.tbcurrency.exception.FormatException;
import org.jakubhajek.tbcurrency.model.Payment;

/**
 * Unit test for Line parser
 */
public class LineParserTest extends TestCase {

	private LineParser parser = new LineParser();
	
	public LineParserTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(LineParserTest.class);
	}
	
	
	public void testNull() throws FormatException {
		assertNull(parser.parse(null));
		assertNull(parser.parse(""));
	}
	
	public void testValid() throws FormatException {
		BigDecimal expectedAmount = new BigDecimal("123");
		Payment result = parser.parse("CZK 123");
		assertEquals(result.getCurrency(), "CZK");
		assertEquals(result.getAmount(), expectedAmount);
	}
	
	public void testValidBlankSpaces() throws FormatException {
		BigDecimal expectedAmount = new BigDecimal("123");
		Payment result = parser.parse("  CZK   123  ");
		assertEquals(result.getCurrency(), "CZK");
		assertEquals(result.getAmount(), expectedAmount);
	}
}
