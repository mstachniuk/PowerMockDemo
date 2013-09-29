package com.blogspot.mstachniuk.powermockdemo.switzerland;

import com.blogspot.mstachniuk.powermockdemo.*;

public class SwitzerlandTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        return SwitzerlandTaxSource.getTax() * product.getNetPrice();
    }
}
