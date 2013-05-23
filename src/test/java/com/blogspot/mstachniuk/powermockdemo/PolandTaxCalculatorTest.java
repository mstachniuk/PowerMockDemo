package com.blogspot.mstachniuk.powermockdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TaxFromFileReader.class)
public class PolandTaxCalculatorTest {

	@Test
	public void shoudMockPrivateMethod() throws Exception {
		// given
		PolandTaxCalculator calculator = new PolandTaxCalculator();
		Product product = new Product("beef", 30);
		TaxFromFileReader taxReader = mock(TaxFromFileReader.class);
		Method methodToMock = PowerMockito.method(TaxFromFileReader.class, "readTaxFromFile",
				Product.class, String.class);
		PowerMockito.doReturn(0.5).when(taxReader, methodToMock);

		// when
		double calculatedTax = calculator.calculateTax(product);

		// then
		assertThat(calculatedTax).isEqualTo(15);
	}
}
