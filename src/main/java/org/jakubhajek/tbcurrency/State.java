package org.jakubhajek.tbcurrency;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jakubhajek.tbcurrency.model.Payment;

/**
 * Class keeping current sums over currencies
 * 
 * @author jakub.hajek
 *
 */
public class State {
	// TreeMap - loop over sums ordered by currencies
	private final  Map<String, BigDecimal> paymentSummary = new TreeMap<String, BigDecimal>();	
	
	/**
	 * Updates sum of currency of given payment by its amount
	 * 
	 * @param payment
	 */
	public synchronized void addPayment(final Payment payment) {
		if (paymentSummary.containsKey(payment.getCurrency())) {
			paymentSummary.put(payment.getCurrency(),
				paymentSummary.get(payment.getCurrency()).add(payment.getAmount()));
		} else {
			paymentSummary.put(payment.getCurrency(), payment.getAmount());
		}
	}
	
	/**
	 * 
	 * Prints the sums of each payment to given print stream
	 * 
	 * @param ps
	 */
	public synchronized void print(PrintStream ps) {
		ps.println("current state:\n----------------");
		for(Entry<String, BigDecimal> e : paymentSummary.entrySet()) {
			if (!BigDecimal.ZERO.equals(e.getValue())) {
				ps.printf("%s %s\n", e.getKey(), NumberFormat.getNumberInstance().format(e.getValue()));
			}
		}
		ps.println("----------------");
	}
}
