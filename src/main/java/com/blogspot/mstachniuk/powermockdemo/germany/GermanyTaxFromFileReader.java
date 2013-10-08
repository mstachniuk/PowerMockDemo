package com.blogspot.mstachniuk.powermockdemo.germany;

import com.blogspot.mstachniuk.powermockdemo.*;

import java.io.*;

public class GermanyTaxFromFileReader {

    private final String filename;

    public GermanyTaxFromFileReader(String filename) {
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
        throw new RuntimeException("Can't read Tax from file: " + filename + " for "
                + product.getName());
    }

}
