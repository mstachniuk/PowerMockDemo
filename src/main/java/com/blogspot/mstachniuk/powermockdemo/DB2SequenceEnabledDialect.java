package com.blogspot.mstachniuk.powermockdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

import org.hibernate.dialect.DB2Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB2SequenceEnabledDialect extends DB2Dialect {

	private static final String SCHEMA_PROPERTY = "db2.schema";

	private static final String DB2_PROPERTIES_FILE = "/db2.properties";

	private static final Logger LOG = LoggerFactory.getLogger(DB2SequenceEnabledDialect.class);

	private static String schemaName;

	static {
		init();
	}

	public static void init() {
		loadProperties();
		checkProperties();
	}

	private static void loadProperties() {
		InputStream is = null;
		try {
			is = DB2SequenceEnabledDialect.class.getResourceAsStream(DB2_PROPERTIES_FILE);
			final Properties properties = new Properties();
			properties.load(is);
			schemaName = properties.getProperty(SCHEMA_PROPERTY);
		} catch (final Exception e) {
			throw new RuntimeException("Error by load Properties from " + DB2_PROPERTIES_FILE, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (final IOException e) {
					// ignore
				}
			}
		}
	}

	private static void checkProperties() {
		LOG.info("Read Schema name from properties: " + schemaName);
		if (schemaName == null) {
			throw new MissingResourceException("I can't find property: " + SCHEMA_PROPERTY
					+ " in file: " + DB2_PROPERTIES_FILE, DB2_PROPERTIES_FILE, SCHEMA_PROPERTY);
		}
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public String getSequenceNextValString(final String sequenceName) {
		final String sql = "SELECT NEXTVAL FOR " + schemaName + "." + sequenceName
				+ " FROM SYSIBM.SYSDUMMY1";
		LOG.debug("Generate SQL Query for sequence: " + sequenceName + ": " + sql);
		return sql;
	}

}
