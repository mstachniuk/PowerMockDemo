package com.blogspot.mstachniuk.powermockdemo.usa;

import com.blogspot.mstachniuk.powermockdemo.*;

public class UsaTaxCalculator implements TaxCalculator {

    private final static int taxRate = 10;

    @Override
    public double calculateTax(Product product) {
        return taxRate * product.getNetPrice();
    }

}
