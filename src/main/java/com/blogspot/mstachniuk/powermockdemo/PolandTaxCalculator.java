package com.blogspot.mstachniuk.powermockdemo;

import java.io.IOException;

public class PolandTaxCalculator implements TaxCalculator {

	private PolandTaxFromFileReader taxFromFileReader;

	@Override
	public double calculateTax(Product product) {
		double taxRate = readTaxRateFromFile(product);
		return taxRate * product.getNetPrice();
	}

	// TODO: How to mock this Method?
	private double readTaxRateFromFile(Product product) {
		try {
			return taxFromFileReader.readTax(product);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// For Tests
	public void setTaxFromFileReader(PolandTaxFromFileReader taxFromFileReader) {
		this.taxFromFileReader = taxFromFileReader;
	}

}
