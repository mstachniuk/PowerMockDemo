package com.blogspot.mstachniuk.powermockdemo;

public class PolandTaxCalculator implements TaxCalculator {

	private final TaxFromFileReader taxFromFileReader = new TaxFromFileReader("polandTax.txt");

	@Override
	public double calculateTax(Product product) {
		double taxRate = readTaxRateFromFile(product);
		return taxRate * product.getNetPrice();
	}

	// TODO: How to mockt this Method?
	private double readTaxRateFromFile(Product product) {

		taxFromFileReader.readTax(product);
		return 0.23;
	}
}
