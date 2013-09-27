package com.blogspot.mstachniuk.powermockdemo;

import java.io.*;

public class GermanTaxCalculator implements TaxCalculator {

    // private final field. How to change this field from test?
    private final GermanTaxFromFileReader taxFromFileReader = new GermanTaxFromFileReader("file.not.exist");

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

}
