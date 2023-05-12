
package edu.ucla.library.libservices.alma.laptops.db;

import java.sql.*;
import edu.ucla.library.libservices.alma.laptops.beans.AvailableItems;
import java.util.List;
import java.util.Properties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataHandler {

    private static final String SQL_INSERT = "INSERT INTO public.\"ALMA_COUNTS\"( \"LOC\", \"CHROMEBOOKS_IN\", \"MAC_LAPTOPS_IN\", \"WIN_LAPTOPS_IN\", \"IPADS_IN\") VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM public.\"ALMA_COUNTS\"";

	public DataHandler() {
		super();
	}

	public static void addRows(final List<AvailableItems> data, final Properties props) {
		final DriverManagerDataSource source = makeConnection(props);
		for (AvailableItems theEntry : data ) {
			new JdbcTemplate( source ).update(SQL_INSERT, new Object[]{theEntry.getLocation(),theEntry.getChromeBooks(),
			                                  theEntry.getMacs(),theEntry.getWindows(),theEntry.getIPads()});
		}
    }

	public static void clearRows(final Properties props) {
		final DriverManagerDataSource source = makeConnection(props);
		new JdbcTemplate( source ).update(SQL_DELETE);
    }

    private static DriverManagerDataSource makeConnection(final Properties props) {
		DriverManagerDataSource ds;
		//final Properties extraProps = new Properties();

		//extraProps.setProperty("ssl", "true");

		ds = new DriverManagerDataSource();
		ds.setDriverClassName(props.getProperty("db.driver"));
		ds.setUrl(props.getProperty("db.url"));
		ds.setUsername(props.getProperty("db.user"));
		ds.setPassword(props.getProperty("db.password"));
		//ds.setConnectionProperties(extraProps);

		return ds;
	}
}