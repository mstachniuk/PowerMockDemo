package com.blogspot.mstachniuk.powermockdemo;

public class PolandTaxCalculator implements TaxCalculator {

	private TaxFromFileReader taxFromFileReader;

	@Override
	public double calculateTax(Product product) {
		double taxRate = readTaxRateFromFile(product);
		return taxRate * product.getNetPrice();
	}

	// TODO: How to mock this Method?
	private double readTaxRateFromFile(Product product) {

		return taxFromFileReader.readTax(product);
	}

	// For Tests
	public void setTaxFromFileReader(TaxFromFileReader taxFromFileReader) {
		this.taxFromFileReader = taxFromFileReader;
	}

}
