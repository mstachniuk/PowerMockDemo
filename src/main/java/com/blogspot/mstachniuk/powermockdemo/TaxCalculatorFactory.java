package com.blogspot.mstachniuk.powermockdemo;

import com.blogspot.mstachniuk.powermockdemo.german.*;
import com.blogspot.mstachniuk.powermockdemo.poland.*;

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
