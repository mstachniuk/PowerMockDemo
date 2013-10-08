package com.blogspot.mstachniuk.powermockdemo.germany;

import com.blogspot.mstachniuk.powermockdemo.*;

import java.io.*;

public class GermanyTaxCalculator implements TaxCalculator {

    private final GermanyTaxFromFileReader taxFromFileReader
            = new GermanyTaxFromFileReader("file.txt");

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
