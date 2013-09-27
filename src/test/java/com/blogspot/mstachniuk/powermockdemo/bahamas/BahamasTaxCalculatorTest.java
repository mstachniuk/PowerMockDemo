package com.blogspot.mstachniuk.powermockdemo.bahamas;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class BahamasTaxCalculatorTest {

    @Test
    public void shouldMockMethod() {
        // given
        BahamasTaxCalculator calculator = new BahamasTaxCalculator();
        TaxRateSource taxRateSourceMock = mock(TaxRateSource.class);
        when(taxRateSourceMock.findTaxByCountry(anyString())).thenReturn(0.1);
        calculator.setTaxSource(taxRateSourceMock);

        // when
        double tax = calculator.calculateTax(new Product("name", 42));

        // then
        assertEquals(4.2, tax, 0.01);
    }
}
