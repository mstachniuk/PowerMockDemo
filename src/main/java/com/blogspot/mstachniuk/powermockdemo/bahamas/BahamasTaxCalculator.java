package com.blogspot.mstachniuk.powermockdemo.bahamas;

import com.blogspot.mstachniuk.powermockdemo.*;

public class BahamasTaxCalculator implements TaxCalculator {

    private TaxRateSource taxSource;

    @Override
    public double calculateTax(Product product) {
        double taxRate = taxSource.findTaxByCountry("bm");
        return taxRate * product.getNetPrice();
    }

    void setTaxSource(TaxRateSource taxSource) {
        this.taxSource = taxSource;
    }
}
