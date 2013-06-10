package com.blogspot.mstachniuk.powermockdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GermanTaxFromFileReader {

	private final String filename;

	public GermanTaxFromFileReader(String filename) {
		this.filename = filename;
	}

	public double readTax(Product product) throws IOException {
		double taxRate = readTaxFromFile(product, filename);
		return taxRate;
	}

	private double readTaxFromFile(Product product, String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		try {
			String entry = reader.readLine();
			while (entry != null) {
				String[] record = entry.split(";");
				if (record[0].equals(product.getName())) {
					return Double.parseDouble(record[1]);
				}
				entry = reader.readLine();
			}
		} finally {
			reader.close();
		}
		return 0;
	}

}
