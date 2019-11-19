package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
    protected static final String USER_DAO = "dao.ua.nure.cs.huriev.usermanagement.db.UserDao";
    protected static Properties properties;

    protected static DaoFactory instance;
    private static final String DAO_FACTORY = "dao.factory";

    public static synchronized DaoFactory getInstance() {
        if (instance == null){
            try {
                Class factoryClass = Class.forName(properties
                        .getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    static{
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getResourceAsStream(
                    "settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DaoFactory() { }

    private ConnectionFactory getConnectionFactory() {
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        String url = properties.getProperty("connection.url");
        String driver = properties.getProperty("connection.driver");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public abstract Dao<User> getUserDao();

    public void init(Properties properties){
        this.properties = properties;
        instance = null;
    }
}
