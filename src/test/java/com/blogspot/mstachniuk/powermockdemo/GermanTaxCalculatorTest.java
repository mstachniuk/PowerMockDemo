package com.blogspot.mstachniuk.powermockdemo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PolandTaxFromFileReader.class, PolandTaxCalculator.class })
public class GermanTaxCalculatorTest {

	private final Product product = new Product("beef", 30);

	@Test
	public void shoudMockPrivateFinalField() throws Exception {
		// given
		GermanTaxCalculator calculator = new GermanTaxCalculator();

		PolandTaxFromFileReader reader = PowerMockito.mock(PolandTaxFromFileReader.class);
		when(reader.readTax(product)).thenReturn(0.20);
		Whitebox.setInternalState(calculator, PolandTaxFromFileReader.class, reader);

		// when
		double calculatedTax = calculator.calculateTax(product);

		// then
		assertEquals(6, calculatedTax, 0.01);
	}
}
