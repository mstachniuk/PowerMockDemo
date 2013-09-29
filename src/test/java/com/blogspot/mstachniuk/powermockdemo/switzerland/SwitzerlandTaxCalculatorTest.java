package com.blogspot.mstachniuk.powermockdemo.switzerland;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@MockPolicy(SwitzerlandMockPolicy.class)
public class SwitzerlandTaxCalculatorTest {

    private Product product = new Product("beef", 30);

    @Test
    public void shouldMockStaticInvocation() {
        // given
        SwitzerlandTaxCalculator calculator = new SwitzerlandTaxCalculator();

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6, calculatedTax, 0.01);
    }
}
