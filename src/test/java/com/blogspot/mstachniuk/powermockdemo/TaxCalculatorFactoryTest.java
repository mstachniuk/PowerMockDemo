package com.blogspot.mstachniuk.powermockdemo;

import com.blogspot.mstachniuk.powermockdemo.german.*;
import com.blogspot.mstachniuk.powermockdemo.poland.*;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class TaxCalculatorFactoryTest {

    @Test
    public void shouldReturnTaxFactoryForGermany() {
        // given
        TaxCalculatorFactory factory = new TaxCalculatorFactory();

        // when
        TaxCalculator taxCalculator = factory.getInstance(Country.GERMAN);

        // then
        assertThat(taxCalculator).isInstanceOf(GermanTaxCalculator.class);
    }

    @Test
    public void shouldReturnTaxFactoryForPoland() {
        // given
        TaxCalculatorFactory factory = new TaxCalculatorFactory();

        // when
        TaxCalculator taxCalculator = factory.getInstance(Country.POLAND);

        // then
        assertThat(taxCalculator).isInstanceOf(PolandTaxCalculator.class);
    }

    @Test
    @PrepareForTest(Country.class)
    public void shouldThrowExceptionWhenUseNonExistCountry() throws Exception {
        // given
        TaxCalculatorFactory factory = new TaxCalculatorFactory();
        PowerMockito.mockStatic(Country.class); // Now all static Method return default Answer and we can change behaviour of static Methods
        Country countryMock = mock(Country.class);
        Whitebox.setInternalState(countryMock, "name", "countryMock");
        Whitebox.setInternalState(countryMock, "ordinal", 2);
        // mock static Method
        when(Country.values()).thenReturn(
                new Country[]{Country.POLAND, Country.GERMAN, countryMock});

        // when
        try {
            TaxCalculator instance = factory.getInstance(countryMock);
            // then
            fail("It should throw UnsupportedOperationException, but is: " + instance);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }


}
