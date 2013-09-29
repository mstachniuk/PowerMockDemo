package com.blogspot.mstachniuk.powermockdemo.tunisia;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TunisiaTaxCalculator.class})
public class TunisiaTaxCalculatorTest {

    private static Product product = new Product("beef", 30.0);

    @Test
    public void shouldMockPrivateStaticField() throws IllegalAccessException {
        // given
        TunisiaTaxCalculator calculator = new TunisiaTaxCalculator();
        TunisiaTaxSource source = PowerMockito.mock(TunisiaTaxSource.class);
        PowerMockito.when(source.getTax()).thenReturn(0.2);
        Field field = PowerMockito.field(TunisiaTaxCalculator.class, "source");
        field.set(TunisiaTaxSource.class, source);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6.0, calculatedTax, 0.01);
    }
}
