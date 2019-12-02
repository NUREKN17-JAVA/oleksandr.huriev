package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

public class DaoFactoryImpl extends DaoFactory {

	@Override
	public Dao<User> getUserDao() {
		Dao<User> result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (Dao<User>) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
