package com.blogspot.mstachniuk.powermockdemo;

import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GermanTaxFromFileReader.class})
public class GermanTaxCalculatorTest {

    private final Product product = new Product("beef", 30);

    @Test
    public void shouldMockPrivateFinalField() throws Exception {
        // given
        GermanTaxCalculator calculator = new GermanTaxCalculator();

        GermanTaxFromFileReader reader = PowerMockito.mock(GermanTaxFromFileReader.class);
        when(reader.readTax(product)).thenReturn(0.20);
        Whitebox.setInternalState(calculator, GermanTaxFromFileReader.class, reader);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6, calculatedTax, 0.01);
    }
}
