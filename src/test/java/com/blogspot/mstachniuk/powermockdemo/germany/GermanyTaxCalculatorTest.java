package com.blogspot.mstachniuk.powermockdemo.germany;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GermanyTaxFromFileReader.class})
public class GermanyTaxCalculatorTest {

    private final Product product = new Product("beef", 30);

    @Test
    public void shouldMockPrivateFinalField() throws Exception {
        // given
        GermanyTaxCalculator calculator = new GermanyTaxCalculator();

        GermanyTaxFromFileReader reader = PowerMockito.mock(GermanyTaxFromFileReader.class);
        when(reader.readTax(product)).thenReturn(0.20);
        Whitebox.setInternalState(calculator, GermanyTaxFromFileReader.class, reader);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6, calculatedTax, 0.01);
    }
}
