package com.blogspot.mstachniuk.powermockdemo.russia;

import com.blogspot.mstachniuk.powermockdemo.*;

// Class is final
public final class RussiaTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        double taxRate = updateActualTaxRate(product);
        return taxRate * product.getNetPrice();
    }

    // private static final Method
    private static final double updateActualTaxRate(Product product) {
        if (product.getName().startsWith("b")) {
            return 0.5;
        } else {
            return 0.4;
        }
    }

}
