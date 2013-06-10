package com.blogspot.mstachniuk.powermockdemo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ GermanTaxFromFileReader.class, BufferedReader.class })
public class GermanTaxFromFileReaderTest {

	private static final String BEEF_TEXT = "beef";
	private static final String SEPARATOR = ";";
	private static final double EXPECTED_TAX = 20;

	private final Product product = new Product(BEEF_TEXT, 30);

	@Test
	public void shoudMockBufferedReaderConstructor() throws Exception {
		// given
		GermanTaxFromFileReader reader = new GermanTaxFromFileReader("fake");

		BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
		when(readerMock.readLine()).thenReturn(BEEF_TEXT + SEPARATOR + EXPECTED_TAX);

		// when called new BufferedReader(Reader) return mock
		Constructor<BufferedReader> constructor = Whitebox.getConstructor(BufferedReader.class,
				Reader.class);
		PowerMockito.whenNew(constructor)//
				.withArguments(Mockito.any(Reader.class))//
				.thenReturn(readerMock);

		// when called new FileReader(String) return null
		Constructor<FileReader> constructorFileReader = Whitebox.getConstructor(FileReader.class,
				String.class);
		PowerMockito.whenNew(constructorFileReader)//
				.withArguments(Mockito.any(String.class))//
				.thenReturn(null);

		// when
		double readTax = reader.readTax(product);

		// then
		assertEquals(20, readTax, 0.01);
	}

	@Test
	public void shoudMockBufferedReaderConstructorAndReadMoreLines() throws Exception {
		// given
		GermanTaxFromFileReader reader = new GermanTaxFromFileReader("fake");

		BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
		// return more lines
		when(readerMock.readLine()).thenReturn("milk;50",//
				"water;10",//
				BEEF_TEXT + SEPARATOR + EXPECTED_TAX,//
				"pizza;101");

		// when called new BufferedReader(Reader) return mock
		Constructor<BufferedReader> constructor = Whitebox.getConstructor(BufferedReader.class,
				Reader.class);
		PowerMockito.whenNew(constructor)//
				.withArguments(Mockito.any(Reader.class))//
				.thenReturn(readerMock);

		// when called new FileReader(String) return null
		Constructor<FileReader> constructorFileReader = Whitebox.getConstructor(FileReader.class,
				String.class);
		PowerMockito.whenNew(constructorFileReader)//
				.withArguments(Mockito.any(String.class))//
				.thenReturn(null);

		// when
		double readTax = reader.readTax(product);

		// then
		assertEquals(20, readTax, 0.01);
	}

	@Test
	public void shoudThrowEception() throws Exception {
		// given
		GermanTaxFromFileReader reader = new GermanTaxFromFileReader("fake");

		BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
		// return more lines
		when(readerMock.readLine()).thenReturn("bad.line", "bad.line", null);

		// when called new BufferedReader(Reader) return mock
		Constructor<BufferedReader> constructor = Whitebox.getConstructor(BufferedReader.class,
				Reader.class);
		PowerMockito.whenNew(constructor)//
				.withArguments(Mockito.any(Reader.class))//
				.thenReturn(readerMock);

		// when called new FileReader(String) return null
		Constructor<FileReader> constructorFileReader = Whitebox.getConstructor(FileReader.class,
				String.class);
		PowerMockito.whenNew(constructorFileReader)//
				.withArguments(Mockito.any(String.class))//
				.thenReturn(null);

		try {
			// when
			reader.readTax(product);
			fail("It should throw RuntimeException");
		} catch (RuntimeException e) {
			// then
			assertTrue(true);
		}
	}
}
