package com.blogspot.mstachniuk.powermockdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TaxFromFileReader.class, PolandTaxCalculator.class })
public class PolandTaxCalculatorTest {

	@Test
	public void shoudMockPrivateMethodInDOC() throws Exception {
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

	@Test
	public void shoudMockPrivateMethodInSUT() throws Exception {
		// given
		PolandTaxCalculator calculator = PowerMockito.spy(new PolandTaxCalculator());
		Product product = new Product("beef", 30);
		Method methodToMock = PowerMockito.method(PolandTaxCalculator.class, "readTaxRateFromFile",
				Product.class);

		PowerMockito.doReturn(new Double(0.75))//
				.when(calculator, methodToMock)//
				.withArguments(any(Product.class));

		// don't work, invoke real method and throw NPE.
		// PowerMockito.when(calculator, methodToMock) //
		// .withArguments(any(Product.class))//
		// .thenReturn(new Double(0.75));

		// when
		double calculatedTax = calculator.calculateTax(product);

		// then
		assertThat(calculatedTax).isEqualTo(22.5);
	}
}
