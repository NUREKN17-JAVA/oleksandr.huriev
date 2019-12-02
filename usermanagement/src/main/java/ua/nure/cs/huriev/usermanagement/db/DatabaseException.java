package ua.nure.cs.huriev.usermanagement.db;

public class DatabaseException extends Exception {

	public DatabaseException(Exception e) {
		super(e);
	}

	public DatabaseException(String str) {
		super(str);
	}

}