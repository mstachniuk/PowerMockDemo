package com.blogspot.mstachniuk.powermockdemo.poland;

import com.blogspot.mstachniuk.powermockdemo.*;

import java.io.*;

public class PolandTaxFromFileReader {

    private final String filename;

    public PolandTaxFromFileReader(String filename) {
        this.filename = filename;
    }

    public double readTax(Product product) throws IOException {
        double taxRate = readTaxFromFile(product, filename);
        return taxRate;
    }

    private double readTaxFromFile(Product product, String filename) {
        return 0;
    }

}
