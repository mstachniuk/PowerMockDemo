package com.blogspot.mstachniuk.powermockdemo;

public class TaxFromFileReader {

	private final String filename;

	public TaxFromFileReader(String filename) {
		this.filename = filename;
	}

	public double readTax(Product product) {
		double taxRate = readTaxFromFile(product, filename);
		return taxRate;
	}

	private double readTaxFromFile(Product product, String filename) {
		// TODO: read from file parsing return smth
		return 0;
	}

}
