package com.blogspot.mstachniuk.powermockdemo.russia;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RussiaTaxCalculator.class})
public class RussiaTaxCalculatorTest {

    private final Product product = new Product("beef", 30);

    @Test
    public void shouldMockPrivateStaticMethod() throws Exception {
        // given
        PowerMockito.mockStatic(RussiaTaxCalculator.class,
                withSettings().defaultAnswer(Mockito.CALLS_REAL_METHODS));
        RussiaTaxCalculator calculator = new RussiaTaxCalculator();
        // static final method
        Method method = Whitebox.getMethod(RussiaTaxCalculator.class,
                "updateActualTaxRate", Product.class);
        PowerMockito.when(RussiaTaxCalculator.class, method)//
                .withArguments(product).thenReturn(0.6);

        // when
        double calculatedTax = calculator.calculateTax(product);

        // then
        assertEquals(18, calculatedTax, 0.01);
    }
}
