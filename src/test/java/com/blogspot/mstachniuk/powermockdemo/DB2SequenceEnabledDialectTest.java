package com.blogspot.mstachniuk.powermockdemo;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;
import org.slf4j.*;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DB2SequenceEnabledDialect.class)
// ignore static block in DB2SequenceEnabledDialect
@SuppressStaticInitializationFor("com.blogspot.mstachniuk.powermockdemo.DB2SequenceEnabledDialect")
public class DB2SequenceEnabledDialectTest {

    @Test
    public void shouldGenerateSequenceNexValue() {
        // given
        final String sequenceName = "xxx";
        final String schemaName = "abc";
        // init Logger
        Whitebox.setInternalState(DB2SequenceEnabledDialect.class, mock(Logger.class));

        final DB2SequenceEnabledDialect dialekt = new DB2SequenceEnabledDialect();
        Whitebox.setInternalState(DB2SequenceEnabledDialect.class, "schemaName", schemaName);

        // when
        final String nextValue = dialekt.getSequenceNextValString(sequenceName);

        // then
        assertEquals("SELECT NEXTVAL FOR " + schemaName + "." + sequenceName
                + " FROM SYSIBM.SYSDUMMY1", nextValue);
    }

    @Test
    public void shouldSupportSequences() {
        // given
        final DB2SequenceEnabledDialect dialekt = new DB2SequenceEnabledDialect();

        // when
        final boolean supportsSequences = dialekt.supportsSequences();

        // then
        assertTrue("This Dialect should support sequences", supportsSequences);
    }

    @Test
    public void shouldThrowExceptionWhenDb2PropertyFileDoesntExist() throws Exception {
        try {
            Whitebox.invokeMethod(DB2SequenceEnabledDialect.class, "init", new Object[]{});
            fail("It should throw RuntimeException");
        } catch (final RuntimeException e) {
            assertEquals("Error by load Properties from /db2.properties", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenSchemaPropertyDoesntExistInFile() throws Exception {
        // given
        // mock static Method.
        PowerMockito.mockStatic(DB2SequenceEnabledDialect.class, Mockito.CALLS_REAL_METHODS);
        final InputStream is = mock(InputStream.class); // empty stream
        PowerMockito.when(DB2SequenceEnabledDialect.class.getResourceAsStream(anyString()))
                .thenReturn(is);

        // init Logger
        Whitebox.setInternalState(DB2SequenceEnabledDialect.class, mock(Logger.class));

        try {
            // when
            Whitebox.invokeMethod(DB2SequenceEnabledDialect.class, "init", new Object[]{});
            fail("It should throw RuntimeException");
        } catch (final RuntimeException e) {
            // then
            assertEquals("I can't find property: db2.schema in file: /db2.properties",
                    e.getMessage());
        }
    }

    @Test
    public void shouldThrowIOExceptionWhenInputStreamCloseMockVoidMethod() throws Exception {
        // given
        // mock static Method.
        PowerMockito.mockStatic(DB2SequenceEnabledDialect.class, Mockito.CALLS_REAL_METHODS);
        final InputStream is = mock(InputStream.class); // empty stream
        PowerMockito.when(DB2SequenceEnabledDialect.class.getResourceAsStream(anyString()))
                .thenReturn(is);
        // Mock void Method, throw Exception
        PowerMockito.doThrow(new IOException()).when(is).close();

        // init Logger
        Whitebox.setInternalState(DB2SequenceEnabledDialect.class, mock(Logger.class));

        try {
            // when
            Whitebox.invokeMethod(DB2SequenceEnabledDialect.class, "init", new Object[]{});
            fail("It should throw RuntimeException");
        } catch (final RuntimeException e) {
            // then
            assertEquals("I can't find property: db2.schema in file: /db2.properties",
                    e.getMessage());
        }
    }
}
