package org.jakubhajek.tbcurrency.model;

import java.math.BigDecimal;


public class Payment {
	private final String currency;
	private final BigDecimal amount;
	
	public Payment(final String currency, final BigDecimal amount) {
		super();
		if (amount == null || currency == null || currency.isEmpty()) {
			throw new IllegalArgumentException("payment attributes cannot be empty");
		}
		this.amount = amount;
		this.currency = currency;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}

}
