package com.blogspot.mstachniuk.powermockdemo.tunisia;

import com.blogspot.mstachniuk.powermockdemo.*;

public class TunisiaTaxCalculator implements TaxCalculator {

    private final static TunisiaTaxSource source = new TunisiaTaxSource();

    @Override
    public double calculateTax(Product product) {
        return source.getTax() * product.getNetPrice();
    }
}

