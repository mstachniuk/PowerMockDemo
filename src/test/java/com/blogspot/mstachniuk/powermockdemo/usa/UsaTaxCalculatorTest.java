package com.blogspot.mstachniuk.powermockdemo.usa;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UsaTaxCalculator.class})
@SuppressStaticInitializationFor("com.blogspot.mstachniuk.powermockdemo.usa.UsaTaxCalculator")
public class UsaTaxCalculatorTest {

    private final Product product = new Product("beef", 30);

    @Ignore
    @Test
    public void shouldMockPrivateStaticPrimitiveField() throws IllegalAccessException {
        // given
        UsaTaxCalculator calculator = new UsaTaxCalculator();
        Field field = PowerMockito.field(UsaTaxCalculator.class, "taxRate");
        field.set(UsaTaxCalculator.class, 5);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(6, calculatedTax, 0.01);
    }
}
