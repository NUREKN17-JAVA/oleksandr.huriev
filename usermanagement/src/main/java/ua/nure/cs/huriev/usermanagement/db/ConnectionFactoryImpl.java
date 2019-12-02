package ua.nure.cs.huriev.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {
	private static final String CONNECTION_DRIVER = "connection.driver";
	private static final String CONNECTION_URL = "connection.url";
	private static final String CONNECTION_USER = "connection.user";
	private static final String CONNECTION_PASSWORD = "connection.password";


	private String url;
	private String user;
	private String password;
	private String driver;

	public ConnectionFactoryImpl(Properties properties) {
		driver = properties.getProperty(CONNECTION_DRIVER);
		url = properties.getProperty(CONNECTION_URL);;
		user = properties.getProperty(CONNECTION_USER);;
		password = properties.getProperty(CONNECTION_PASSWORD);;
	}

	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ConnectionFactoryImpl() {	}
	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}


}
