package com.blogspot.mstachniuk.powermockdemo.switzerland;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SwitzerlandTaxSource.class)
public class SwitzerlandTaxCalculatorWithoutPolicyTest {

    @Test
    public void shouldMockStaticInvocation() {
        // given
        SwitzerlandTaxCalculator calculator = new SwitzerlandTaxCalculator();
        PowerMockito.mockStatic(SwitzerlandTaxSource.class, Mockito.RETURNS_DEFAULTS);
        PowerMockito.when(SwitzerlandTaxSource.getTax()).thenReturn(0.2);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6, calculatedTax, 0.01);
    }

    private Product product = new Product("beef", 30);

}
