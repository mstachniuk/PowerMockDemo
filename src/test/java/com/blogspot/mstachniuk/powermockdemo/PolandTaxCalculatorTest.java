package com.blogspot.mstachniuk.powermockdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

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
		TaxFromFileReader taxReader = PowerMockito.spy(new TaxFromFileReader("fake"));
		Method methodToMock = PowerMockito.method(TaxFromFileReader.class, "readTaxFromFile",
				Product.class, String.class);
		PowerMockito.when(taxReader, methodToMock)//
				.withArguments(any(Product.class), anyString())//
				.thenReturn(new Double(0.5));
		// bug
		// PowerMockito.doReturn(new Double(0.5)).when(taxReader, methodToMock)
		// .withArguments(any(Product.class), anyString());

		calculator.setTaxFromFileReader(taxReader);

		// when
		double calculatedTax = calculator.calculateTax(product);

		// then
		assertThat(calculatedTax).isEqualTo(15);
	}
}
