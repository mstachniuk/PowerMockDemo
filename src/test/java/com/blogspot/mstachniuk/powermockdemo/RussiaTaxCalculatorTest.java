package com.blogspot.mstachniuk.powermockdemo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.withSettings;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ RussiaTaxCalculator.class })
public class RussiaTaxCalculatorTest {

	private final Product product = new Product("beef", 30);

	@Test
	public void shoudMockPrivateStaticMethod() throws Exception {
		// given
		PowerMockito.mockStatic(RussiaTaxCalculator.class,
				withSettings().defaultAnswer(Mockito.CALLS_REAL_METHODS));
		RussiaTaxCalculator calculator = new RussiaTaxCalculator();
		// static final method
		Method method = Whitebox.getMethod(RussiaTaxCalculator.class, "updateActualTaxRate",
				Product.class);
		PowerMockito.when(RussiaTaxCalculator.class, method)//
				.withArguments(product).thenReturn(0.6);
		// updateActualTaxRate should return 0.6

		// when
		double calculatedTax = calculator.calculateTax(product);

		// then
		assertEquals(18, calculatedTax, 0.01);
	}
}
