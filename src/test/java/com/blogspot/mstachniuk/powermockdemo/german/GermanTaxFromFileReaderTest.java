package com.blogspot.mstachniuk.powermockdemo.german;

import com.blogspot.mstachniuk.powermockdemo.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;

import java.io.*;
import java.lang.reflect.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GermanTaxFromFileReader.class)
public class GermanTaxFromFileReaderTest {

    private final Product product = new Product("beef", 30);

    @Test
    public void shouldMockBufferedReaderConstructor() throws Exception {
        // given
        GermanTaxFromFileReader reader = new GermanTaxFromFileReader("fake");

        BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
        when(readerMock.readLine()).thenReturn("beef;20.0");

        // when called new BufferedReader(Reader) return mock
        Constructor<BufferedReader> constructor = Whitebox.getConstructor(
                BufferedReader.class, Reader.class);
        PowerMockito.whenNew(constructor)//
                .withArguments(Mockito.any(Reader.class))//
                .thenReturn(readerMock);

        // when called new FileReader(String) return null
        Constructor<FileReader> constructorFileReader = Whitebox.getConstructor(
                FileReader.class, String.class);
        PowerMockito.whenNew(constructorFileReader)//
                .withArguments(Matchers.any(String.class))//
                .thenReturn(null);

        // when
        double readTax = reader.readTax(product);

        // then
        assertEquals(20, readTax, 0.01);
    }

    @Test
    public void shouldMockBufferedReaderConstructorAndReadMoreLines() throws Exception {
        // given
        GermanTaxFromFileReader reader = new GermanTaxFromFileReader("fake");

        BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
        // return more lines
        when(readerMock.readLine()).thenReturn("milk;50",//
                "water;10",//
                "beef" + ";" + (double) 20,//
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
    public void shouldThrowException() throws Exception {
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
