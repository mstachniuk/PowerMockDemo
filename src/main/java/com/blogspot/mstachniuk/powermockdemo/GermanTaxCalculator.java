package com.blogspot.mstachniuk.powermockdemo;

public class GermanTaxCalculator implements TaxCalculator {

	@Override
	public double calculateTax(Product product) {
		throw new UnsupportedOperationException("This Method ist not implement!");
	}
}
