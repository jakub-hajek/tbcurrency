package org.jakubhajek.tbcurrency;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jakubhajek.tbcurrency.exception.FormatException;
import org.jakubhajek.tbcurrency.model.Payment;


public class LineParser {
	private static final Pattern PATTERN = Pattern.compile("^\\s*(\\S+)\\s+(\\S+)\\s*$");
	
	public Payment parse(final String input) throws FormatException {
		
		if (input == null || input.isEmpty()) {
			return null;
		}
	    final Matcher matcher = LineParser.PATTERN.matcher(input);
	    try {
	    	if (matcher.find()) {
	    		return new Payment(matcher.group(1), new BigDecimal(matcher.group(2)));
	    	}
		} catch (NumberFormatException e) {
			//exception is thrown as a default result
		}
		throw new FormatException();
	}

}
