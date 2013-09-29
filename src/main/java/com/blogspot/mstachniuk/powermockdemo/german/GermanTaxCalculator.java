package com.blogspot.mstachniuk.powermockdemo.german;

import com.blogspot.mstachniuk.powermockdemo.*;

import java.io.*;

public class GermanTaxCalculator implements TaxCalculator {

    private final GermanTaxFromFileReader taxFromFileReader
            = new GermanTaxFromFileReader("file.txt");

    @Override
    public double calculateTax(Product product) {
        double taxRate = readTaxRateFromFile(product);
        return taxRate * product.getNetPrice();
    }

    private double readTaxRateFromFile(Product product) {
        try {
            return taxFromFileReader.readTax(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
