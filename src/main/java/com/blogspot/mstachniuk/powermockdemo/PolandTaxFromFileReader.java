package com.blogspot.mstachniuk.powermockdemo;

import java.io.IOException;

public class PolandTaxFromFileReader {

	private final String filename;

	public PolandTaxFromFileReader(String filename) {
		this.filename = filename;
	}

	public double readTax(Product product) throws IOException {
		double taxRate = readTaxFromFile(product, filename);
		return taxRate;
	}

	private double readTaxFromFile(Product product, String filename) throws IOException {
		return 0;
	}

}
