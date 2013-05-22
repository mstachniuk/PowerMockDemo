package com.blogspot.mstachniuk.powermockdemo;

public class TaxCalculatorFactory {

    public TaxCalculator getInstance(Country country) {
        switch (country) {
            case GERMAN:
                return new GermanTaxCalculator();
            case POLAND:
                return new PolandTaxCalculator();
            default:
                throw new UnsupportedOperationException("No supported Tax Calculator for " + country.name());
        }
    }
}
